package br.com.alfabetiza.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rodadas")
public class Rodada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Column(nullable = false)
    private String modalidade;

    @Column(nullable = false, length = 1500)
    private String atividadeIds;

    @Column(nullable = false)
    private int respondidas;

    @Column(nullable = false)
    private int acertos;

    @Column(nullable = false)
    private int pontos;

    @Column(nullable = false)
    private LocalDateTime iniciadaEm;

    private LocalDateTime concluidaEm;

    @Version
    private Long versao;

    public Long getId() { return id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }
    public String getAtividadeIds() { return atividadeIds; }
    public void setAtividadeIds(String atividadeIds) { this.atividadeIds = atividadeIds; }
    public int getRespondidas() { return respondidas; }
    public void setRespondidas(int respondidas) { this.respondidas = respondidas; }
    public int getAcertos() { return acertos; }
    public void setAcertos(int acertos) { this.acertos = acertos; }
    public int getPontos() { return pontos; }
    public void setPontos(int pontos) { this.pontos = pontos; }
    public LocalDateTime getIniciadaEm() { return iniciadaEm; }
    public void setIniciadaEm(LocalDateTime iniciadaEm) { this.iniciadaEm = iniciadaEm; }
    public LocalDateTime getConcluidaEm() { return concluidaEm; }
    public void setConcluidaEm(LocalDateTime concluidaEm) { this.concluidaEm = concluidaEm; }
}
