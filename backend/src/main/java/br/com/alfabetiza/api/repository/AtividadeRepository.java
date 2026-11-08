package br.com.alfabetiza.api.repository;

import br.com.alfabetiza.api.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
