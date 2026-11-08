package br.com.alfabetiza.api.service;

import br.com.alfabetiza.api.dto.AlunoRequest;
import br.com.alfabetiza.api.model.Aluno;
import br.com.alfabetiza.api.model.Responsavel;
import br.com.alfabetiza.api.repository.AlunoRepository;
import br.com.alfabetiza.api.repository.ResponsavelRepository;
import br.com.alfabetiza.api.repository.ResultadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AlunoService {
    private final AlunoRepository alunos;
    private final ResponsavelRepository responsaveis;
    private final ResultadoRepository resultados;

    public AlunoService(
            AlunoRepository alunos,
            ResponsavelRepository responsaveis,
            ResultadoRepository resultados
    ) {
        this.alunos = alunos;
        this.responsaveis = responsaveis;
        this.resultados = resultados;
    }

    public List<Aluno> listarTodos() {
        return alunos.findAll();
    }

    public Aluno buscar(Long id) {
        return alunos.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado"));
    }

    public Aluno cadastrar(AlunoRequest requisicao) {
        Aluno aluno = new Aluno();
        aplicar(aluno, requisicao);
        return alunos.save(aluno);
    }

    public Aluno atualizar(Long id, AlunoRequest requisicao) {
        Aluno aluno = buscar(id);
        aplicar(aluno, requisicao);
        return alunos.save(aluno);
    }

    private void aplicar(Aluno aluno, AlunoRequest requisicao) {
        Responsavel responsavel = responsaveis.findById(requisicao.responsavelId())
                .orElseThrow(() -> new NoSuchElementException("Responsável não encontrado"));

        aluno.setNome(requisicao.nome().trim());
        aluno.setIdade(requisicao.idade());
        aluno.setResponsavel(responsavel);
    }

    @Transactional
    public void excluir(Long id) {
        buscar(id);
        resultados.deleteByAlunoId(id);
        alunos.deleteById(id);
    }
}
