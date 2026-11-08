package br.com.alfabetiza.api.dto;

import jakarta.validation.constraints.*;

public record ResponsavelRequest(@NotBlank String nome, @Email String email) {
}
