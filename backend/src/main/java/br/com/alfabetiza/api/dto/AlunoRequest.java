package br.com.alfabetiza.api.dto;

import jakarta.validation.constraints.*;

public record AlunoRequest(
        @NotBlank String nome,
        @NotNull @Min(3) @Max(18) Integer idade,
        Long responsavelId
) {
}
