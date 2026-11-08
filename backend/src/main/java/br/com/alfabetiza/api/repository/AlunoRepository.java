package br.com.alfabetiza.api.repository;

import br.com.alfabetiza.api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    java.util.List<Aluno> findByResponsavelId(Long responsavelId);
}
