package br.com.alfabetiza.api.repository;

import br.com.alfabetiza.api.model.Rodada;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RodadaRepository extends JpaRepository<Rodada, Long> {
    List<Rodada> findByAlunoIdAndConcluidaEmIsNotNullOrderByConcluidaEmDesc(Long alunoId);
    void deleteByAlunoId(Long alunoId);
}
