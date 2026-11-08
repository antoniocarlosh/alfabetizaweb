package br.com.alfabetiza.api.service;

import br.com.alfabetiza.api.dto.*;
import br.com.alfabetiza.api.model.*;
import br.com.alfabetiza.api.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JogoService {
    private final AlunoRepository alunos;
    private final AtividadeRepository atividades;
    private final ResultadoRepository resultados;

    public JogoService(AlunoRepository alunos, AtividadeRepository atividades, ResultadoRepository resultados) {
        this.alunos = alunos;
        this.atividades = atividades;
        this.resultados = resultados;
    }

    public Resultado responder(RespostaRequest requisicao) {
        Aluno aluno = alunos.findById(requisicao.alunoId())
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado"));
        Atividade atividade = atividades.findById(requisicao.atividadeId())
                .orElseThrow(() -> new NoSuchElementException("Atividade não encontrada"));

        String resposta = requisicao.resposta().trim();
        boolean opcaoValida = Arrays.stream(atividade.getOpcoes().split("\\|"))
                .anyMatch(opcao -> opcao.trim().equalsIgnoreCase(resposta));

        if (!opcaoValida) {
            throw new IllegalArgumentException("A resposta precisa ser uma das alternativas da atividade.");
        }

        boolean correto = atividade.getRespostaCorreta().trim().equalsIgnoreCase(resposta);
        Resultado resultado = new Resultado();
        resultado.setAluno(aluno);
        resultado.setAtividade(atividade);
        resultado.setResposta(resposta);
        resultado.setCorreto(correto);
        resultado.setPontosObtidos(correto ? atividade.getPontos() : 0);
        resultado.setRealizadoEm(LocalDateTime.now());
        return resultados.save(resultado);
    }

    public List<Resultado> historico(Long alunoId) {
        if (!alunos.existsById(alunoId)) {
            throw new NoSuchElementException("Aluno não encontrado");
        }
        return resultados.findByAlunoIdOrderByRealizadoEmDesc(alunoId);
    }

    public ProgressoResponse progresso(Long alunoId) {
        List<Resultado> lista = historico(alunoId);
        long acertos = lista.stream().filter(resultado -> Boolean.TRUE.equals(resultado.getCorreto())).count();
        int pontos = lista.stream().mapToInt(resultado -> resultado.getPontosObtidos() == null ? 0 : resultado.getPontosObtidos()).sum();
        double percentual = lista.isEmpty() ? 0 : Math.round(acertos * 10000.0 / lista.size()) / 100.0;
        return new ProgressoResponse(alunoId, lista.size(), acertos, pontos, percentual);
    }

    public List<AtividadePontuacaoResponse> pontosPorAtividade(Long alunoId) {
        Map<Long, List<Resultado>> agrupados = historico(alunoId).stream()
                .collect(Collectors.groupingBy(resultado -> resultado.getAtividade().getId()));

        return agrupados.values().stream().map(lista -> {
            Atividade atividade = lista.get(0).getAtividade();
            long acertos = lista.stream().filter(resultado -> Boolean.TRUE.equals(resultado.getCorreto())).count();
            int pontos = lista.stream().mapToInt(resultado -> resultado.getPontosObtidos() == null ? 0 : resultado.getPontosObtidos()).sum();
            return new AtividadePontuacaoResponse(
                    atividade.getId(), atividade.getTitulo(), atividade.getTipo(), lista.size(), acertos, pontos
            );
        }).sorted(Comparator.comparing(AtividadePontuacaoResponse::tipo)
                .thenComparing(AtividadePontuacaoResponse::titulo)).toList();
    }
}
