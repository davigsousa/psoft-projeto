package com.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
public class SolicitacaoOrientacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String resposta;
    private Boolean isAprovado;
    private String solicitante;
    @ManyToOne()
    private Professor professor;
    @OneToOne
    private PropostaTCC proposta;
    @OneToOne
    private Aluno aluno;

    public SolicitacaoOrientacao() {}

    public SolicitacaoOrientacao(PropostaTCC proposta, Professor professor, Aluno aluno) {
        this.proposta = proposta;
        this.isAprovado = false;
        this.solicitante = "PROFESSOR";
        this.professor = professor;
        this.aluno = aluno;
    }

    public SolicitacaoOrientacao(PropostaTCC proposta, Aluno aluno) {
        this.proposta = proposta;
        this.isAprovado = false;
        this.solicitante = "ALUNO";
        this.aluno = aluno;
        this.professor = proposta.getProfessor();
    }

    public Long getId() {
        return id;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Boolean getAprovado() {
        return isAprovado;
    }

    public void setAprovado(Boolean aprovado) {
        isAprovado = aprovado;
    }

    public PropostaTCC getProposta() {
        return proposta;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setProposta(PropostaTCC proposta) {
        this.proposta = proposta;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
