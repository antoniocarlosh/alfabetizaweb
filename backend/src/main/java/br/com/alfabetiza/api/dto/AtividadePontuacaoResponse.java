package br.com.alfabetiza.api.dto;

public record AtividadePontuacaoResponse(
        Long atividadeId,
        String titulo,
        String tipo,
        long tentativas,
        long acertos,
        int pontos
) {
}
