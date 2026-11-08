package br.com.alfabetiza.api.dto;

import jakarta.validation.constraints.*;

public record RespostaRequest(@NotNull Long alunoId, @NotNull Long atividadeId, @NotBlank String resposta) {
}
