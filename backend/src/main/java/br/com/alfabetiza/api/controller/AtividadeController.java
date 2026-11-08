package br.com.alfabetiza.api.controller;

import br.com.alfabetiza.api.model.Atividade;
import br.com.alfabetiza.api.repository.AtividadeRepository;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {
    private final AtividadeRepository repository;

    public AtividadeController(AtividadeRepository repository) {
        this.repository = repository;
    }

    public record AtividadePublica(
            Long id,
            String titulo,
            String tipo,
            String enunciado,
            List<String> opcoes,
            Integer pontos,
            String imagemUrl
    ) {
    }

    @GetMapping
    public List<AtividadePublica> listar(@RequestParam(required = false) String tipo) {
        List<Atividade> selecionadas = new ArrayList<>(repository.findAll().stream()
                .filter(atividade -> tipo == null || atividade.getTipo().equalsIgnoreCase(tipo))
                .toList());

        Collections.shuffle(selecionadas);

        if (tipo != null && !tipo.equalsIgnoreCase("LETRAS") && selecionadas.size() > 10) {
            selecionadas = new ArrayList<>(selecionadas.subList(0, 10));
        }

        return selecionadas.stream().map(atividade -> {
            List<String> opcoes = new ArrayList<>(Arrays.asList(atividade.getOpcoes().split("\\|")));
            Collections.shuffle(opcoes);
            return new AtividadePublica(
                    atividade.getId(),
                    atividade.getTitulo(),
                    atividade.getTipo(),
                    atividade.getEnunciado(),
                    opcoes,
                    atividade.getPontos(),
                    atividade.getImagemUrl()
            );
        }).toList();
    }
}
