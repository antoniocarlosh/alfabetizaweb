package br.com.alfabetiza.api.dto;

public record ProgressoResponse(Long alunoId, long atividadesRespondidas, long acertos, int pontosTotais, double percentualAcerto) {
}
