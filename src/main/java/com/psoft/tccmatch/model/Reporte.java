package com.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String periodo;
    private String problema;
    @OneToOne
    private Aluno aluno;
    @OneToOne
    private Professor professor;
    @ManyToOne
    private Orientacao orientacao;

    public Reporte() {
    }

    public Reporte(String periodo, String problema, Orientacao orientacao) {
        this.periodo = periodo;
        this.problema = problema;
        this.orientacao = orientacao;
    }

    public Long getId() {
        return id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public Orientacao getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
