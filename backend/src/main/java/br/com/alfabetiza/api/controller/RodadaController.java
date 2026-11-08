package br.com.alfabetiza.api.controller;

import br.com.alfabetiza.api.service.AcessoFamiliaService;
import br.com.alfabetiza.api.service.RodadaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RodadaController {
    private final RodadaService rodadas;
    private final AcessoFamiliaService acesso;

    public RodadaController(RodadaService rodadas, AcessoFamiliaService acesso) {
        this.rodadas = rodadas;
        this.acesso = acesso;
    }

    public record NovaRodada(@NotNull Long alunoId, @NotBlank String modalidade) {}
    public record NovaResposta(@NotNull Long alunoId, @NotNull Long atividadeId,
                               @NotBlank String resposta) {}

    @PostMapping("/rodadas")
    @ResponseStatus(HttpStatus.CREATED)
    public RodadaService.Inicio iniciar(@Valid @RequestBody NovaRodada request, Authentication auth) {
        acesso.alunoAutorizado(request.alunoId(), auth);
        return rodadas.iniciar(request.alunoId(), request.modalidade());
    }

    @PostMapping("/rodadas/{rodadaId}/respostas")
    public RodadaService.Resposta responder(@PathVariable Long rodadaId,
            @Valid @RequestBody NovaResposta request, Authentication auth) {
        acesso.alunoAutorizado(request.alunoId(), auth);
        return rodadas.responder(rodadaId, request.alunoId(), request.atividadeId(), request.resposta());
    }

    @GetMapping("/alunos/{alunoId}/rodadas")
    public List<RodadaService.Painel> painel(@PathVariable Long alunoId, Authentication auth) {
        acesso.alunoAutorizado(alunoId, auth);
        return rodadas.painel(alunoId);
    }
}
