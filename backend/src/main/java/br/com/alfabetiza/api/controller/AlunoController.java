package br.com.alfabetiza.api.controller;

import br.com.alfabetiza.api.dto.AlunoRequest;
import br.com.alfabetiza.api.model.Aluno;
import br.com.alfabetiza.api.repository.AlunoRepository;
import br.com.alfabetiza.api.service.AcessoFamiliaService;
import br.com.alfabetiza.api.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    private final AlunoService service;
    private final AlunoRepository alunos;
    private final AcessoFamiliaService acesso;

    public AlunoController(AlunoService service, AlunoRepository alunos, AcessoFamiliaService acesso) {
        this.service = service;
        this.alunos = alunos;
        this.acesso = acesso;
    }

    @GetMapping
    public List<Aluno> listar(Authentication auth) {
        return alunos.findByResponsavelId(acesso.responsavelId(auth));
    }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id, Authentication auth) {
        return acesso.alunoAutorizado(id, auth);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno cadastrar(@Valid @RequestBody AlunoRequest request, Authentication auth) {
        Long responsavelId = acesso.responsavelId(auth);
        return service.cadastrar(new AlunoRequest(request.nome(), request.idade(), responsavelId));
    }

    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequest request, Authentication auth) {
        acesso.alunoAutorizado(id, auth);
        Long responsavelId = acesso.responsavelId(auth);
        return service.atualizar(id, new AlunoRequest(request.nome(), request.idade(), responsavelId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id, Authentication auth) {
        acesso.alunoAutorizado(id, auth);
        service.excluir(id);
    }
}
