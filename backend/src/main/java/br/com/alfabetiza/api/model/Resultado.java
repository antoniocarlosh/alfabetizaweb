package br.com.alfabetiza.api.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "resultados")
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resposta;
    private Boolean correto;
    private Integer pontosObtidos;
    private java.time.LocalDateTime realizadoEm;
    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    @JsonIgnoreProperties( {
        "hibernateLazyInitializer", "handler"
    }
    )
    private Aluno aluno;
    @ManyToOne(optional = false)
    @JoinColumn(name = "atividade_id", nullable = false)
    @JsonIgnoreProperties( {
        "hibernateLazyInitializer", "handler"
    })
    private Atividade atividade;

    public Resultado() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getResposta() {
        return resposta;
    }
    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    public Boolean getCorreto() {
        return correto;
    }
    public void setCorreto(Boolean correto) {
        this.correto = correto;
    }
    public Integer getPontosObtidos() {
        return pontosObtidos;
    }
    public void setPontosObtidos(Integer pontosObtidos) {
        this.pontosObtidos = pontosObtidos;
    }
    public java.time.LocalDateTime getRealizadoEm() {
        return realizadoEm;
    }
    public void setRealizadoEm(java.time.LocalDateTime realizadoEm) {
        this.realizadoEm = realizadoEm;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public Atividade getAtividade() {
        return atividade;
    }
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
}
