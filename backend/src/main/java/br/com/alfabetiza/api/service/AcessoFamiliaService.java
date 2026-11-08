package br.com.alfabetiza.api.service;

import br.com.alfabetiza.api.controller.AuthController.UsuarioAutenticado;
import br.com.alfabetiza.api.model.Aluno;
import br.com.alfabetiza.api.repository.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AcessoFamiliaService {
    private final AlunoRepository alunos;

    public AcessoFamiliaService(AlunoRepository alunos) {
        this.alunos = alunos;
    }

    public Long responsavelId(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof UsuarioAutenticado usuario)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return usuario.id();
    }

    public Aluno alunoAutorizado(Long alunoId, Authentication authentication) {
        Long responsavelId = responsavelId(authentication);
        Aluno aluno = alunos.findById(alunoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (aluno.getResponsavel() == null ||
            !responsavelId.equals(aluno.getResponsavel().getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return aluno;
    }
}
