package br.com.alfabetiza.api.service;

import br.com.alfabetiza.api.model.*;
import br.com.alfabetiza.api.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RodadaService {
    private final RodadaRepository rodadas;
    private final AtividadeRepository atividades;
    private final AlunoRepository alunos;
    private final JogoService jogo;

    public RodadaService(RodadaRepository rodadas, AtividadeRepository atividades,
                         AlunoRepository alunos, JogoService jogo) {
        this.rodadas = rodadas;
        this.atividades = atividades;
        this.alunos = alunos;
        this.jogo = jogo;
    }

    public record Questao(Long id, String titulo, String tipo, String enunciado,
                         List<String> opcoes, Integer pontos, String imagemUrl) {}

    public record Inicio(Long rodadaId, String modalidade, List<Questao> atividades) {}

    public record Resposta(boolean correto, int pontosObtidos, boolean rodadaConcluida) {}

    public record Resumo(Long id, String modalidade, int acertos, int erros, int pontos,
                         int total, LocalDateTime concluidaEm) {}

    public record Painel(String modalidade, Resumo recorde, List<Resumo> historico) {}

    @Transactional
    public Inicio iniciar(Long alunoId, String modalidade) {
        Aluno aluno = alunos.findById(alunoId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        if (!List.of("LETRAS", "SILABAS", "IMAGEM_PALAVRA").contains(modalidade)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Modalidade inválida");
        }

        List<Atividade> selecionadas = new ArrayList<>(atividades.findAll().stream()
            .filter(a -> modalidade.equalsIgnoreCase(a.getTipo())).toList());
        Collections.shuffle(selecionadas);
        int quantidade = modalidade.equals("LETRAS") ? 26 : 10;
        if (selecionadas.size() < quantidade) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Atividades insuficientes para completar a rodada de " + modalidade);
        }
        selecionadas = new ArrayList<>(selecionadas.subList(0, quantidade));

        Rodada rodada = new Rodada();
        rodada.setAluno(aluno);
        rodada.setModalidade(modalidade);
        rodada.setAtividadeIds(selecionadas.stream()
            .map(a -> a.getId().toString()).reduce((a, b) -> a + "," + b).orElse(""));
        rodada.setIniciadaEm(LocalDateTime.now());
        rodadas.save(rodada);

        List<Questao> questoes = selecionadas.stream().map(a -> {
            List<String> opcoes = new ArrayList<>(Arrays.asList(a.getOpcoes().split("\\|")));
            Collections.shuffle(opcoes);
            return new Questao(a.getId(), a.getTitulo(), a.getTipo(), a.getEnunciado(),
                opcoes, a.getPontos(), a.getImagemUrl());
        }).toList();
        return new Inicio(rodada.getId(), modalidade, questoes);
    }

    @Transactional
    public Resposta responder(Long rodadaId, Long alunoId, Long atividadeId, String resposta) {
        Rodada rodada = rodadas.findById(rodadaId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Rodada não encontrada"));
        if (!rodada.getAluno().getId().equals(alunoId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Rodada não pertence ao aluno");
        }
        if (rodada.getConcluidaEm() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Rodada já concluída");
        }
        String[] ids = rodada.getAtividadeIds().split(",");
        int indice = rodada.getRespondidas();
        if (indice >= ids.length || !ids[indice].equals(atividadeId.toString())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atividade fora da sequência da rodada");
        }
        if (resposta == null || resposta.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resposta obrigatória");
        }
        Atividade atividade = atividades.findById(atividadeId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Atividade não encontrada"));
        if (!atividade.getTipo().equalsIgnoreCase(rodada.getModalidade())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Modalidade incompatível");
        }
        List<String> opcoes = Arrays.asList(atividade.getOpcoes().split("\\|"));
        if (opcoes.stream().noneMatch(o -> o.equals(resposta))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Opção inválida");
        }

        boolean correto = atividade.getRespostaCorreta().trim().equalsIgnoreCase(resposta.trim());
        int pontos = correto ? atividade.getPontos() : 0;
        jogo.responder(new br.com.alfabetiza.api.dto.RespostaRequest(alunoId, atividadeId, resposta));
        rodada.setRespondidas(indice + 1);
        rodada.setAcertos(rodada.getAcertos() + (correto ? 1 : 0));
        rodada.setPontos(rodada.getPontos() + pontos);
        if (rodada.getRespondidas() == ids.length) {
            rodada.setConcluidaEm(LocalDateTime.now());
        }
        rodadas.save(rodada);
        return new Resposta(correto, pontos, rodada.getConcluidaEm() != null);
    }

    @Transactional
    public List<Painel> painel(Long alunoId) {
        if (!alunos.existsById(alunoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
        List<Resumo> historico = rodadas
            .findByAlunoIdAndConcluidaEmIsNotNullOrderByConcluidaEmDesc(alunoId)
            .stream().map(this::resumir).toList();
        return List.of("LETRAS", "SILABAS", "IMAGEM_PALAVRA").stream().map(modalidade -> {
            List<Resumo> lista = historico.stream()
                .filter(r -> r.modalidade().equals(modalidade)).toList();
            Resumo melhor = lista.stream().max(Comparator.comparingInt(Resumo::acertos)
                .thenComparingInt(Resumo::pontos)
                .thenComparing(Resumo::concluidaEm)).orElse(null);
            return new Painel(modalidade, melhor, lista);
        }).toList();
    }

    private Resumo resumir(Rodada rodada) {
        int total = rodada.getAtividadeIds().split(",").length;
        return new Resumo(rodada.getId(), rodada.getModalidade(), rodada.getAcertos(),
            total - rodada.getAcertos(), rodada.getPontos(), total, rodada.getConcluidaEm());
    }
}
