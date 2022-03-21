package com.psoft.tccmatch.model;

import javax.persistence.*;

public class Orientacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private Professor professor;
    private Aluno aluno;
    private String orientacao;
    private String tema;
    private Boolean status;

    public Orientacao () {}

    public Orientacao(Professor professor, Aluno aluno, String orientacao, String tema, Boolean status) {
        this.professor = professor;
        this.aluno = aluno;
        this.orientacao = orientacao;
        this.tema = tema;
        this.status = status;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(String orientacao) {
        this.orientacao = orientacao;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
