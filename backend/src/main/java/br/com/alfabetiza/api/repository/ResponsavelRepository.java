package br.com.alfabetiza.api.repository;

import br.com.alfabetiza.api.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    Optional<Responsavel> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);
}
