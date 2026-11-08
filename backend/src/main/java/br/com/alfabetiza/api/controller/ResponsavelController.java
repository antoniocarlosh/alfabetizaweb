package br.com.alfabetiza.api.controller;

import br.com.alfabetiza.api.model.Aluno;
import br.com.alfabetiza.api.model.Responsavel;
import br.com.alfabetiza.api.repository.AlunoRepository;
import br.com.alfabetiza.api.repository.ResponsavelRepository;
import br.com.alfabetiza.api.service.AcessoFamiliaService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController {
    private final ResponsavelRepository responsaveis;
    private final AlunoRepository alunos;
    private final AcessoFamiliaService acesso;

    public ResponsavelController(
        ResponsavelRepository responsaveis,
        AlunoRepository alunos,
        AcessoFamiliaService acesso
    ) {
        this.responsaveis = responsaveis;
        this.alunos = alunos;
        this.acesso = acesso;
    }

    @GetMapping
    public List<Responsavel> listar(Authentication auth) {
        return List.of(buscarAtual(auth));
    }

    @GetMapping("/{id}")
    public Responsavel buscar(@PathVariable Long id, Authentication auth) {
        if (!id.equals(acesso.responsavelId(auth))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return buscarAtual(auth);
    }

    @GetMapping("/{id}/alunos")
    public List<Aluno> alunos(@PathVariable Long id, Authentication auth) {
        buscar(id, auth);
        return alunos.findByResponsavelId(id);
    }

    private Responsavel buscarAtual(Authentication auth) {
        return responsaveis.findById(acesso.responsavelId(auth))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
