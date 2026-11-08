package br.com.alfabetiza.api.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "atividades")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String tipo;
    private String enunciado;
    private String opcoes;
    private String respostaCorreta;
    private String imagemUrl;
    private Integer pontos;
    public Atividade() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getEnunciado() {
        return enunciado;
    }
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    public String getOpcoes() {
        return opcoes;
    }
    public void setOpcoes(String opcoes) {
        this.opcoes = opcoes;
    }
    public String getRespostaCorreta() {
        return respostaCorreta;
    }
    public void setRespostaCorreta(String respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }
    public String getImagemUrl() {
        return imagemUrl;
    }
    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
    public Integer getPontos() {
        return pontos;
    }
    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
}
