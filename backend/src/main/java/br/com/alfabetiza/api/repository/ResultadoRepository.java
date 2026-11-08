package br.com.alfabetiza.api.repository;

import br.com.alfabetiza.api.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    List<Resultado> findByAlunoIdOrderByRealizadoEmDesc(Long alunoId);

    long deleteByAlunoId(Long alunoId);
}
