package br.com.alfabetiza.api.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsavel_id")
    @JsonIgnoreProperties( {
        "hibernateLazyInitializer", "handler"
    })
    private Responsavel responsavel;

    public Aluno() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    public Responsavel getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}
