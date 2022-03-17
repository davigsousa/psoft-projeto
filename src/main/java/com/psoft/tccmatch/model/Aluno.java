package com.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private String periodo_de_conclusao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno(String nome, String matricula, String email, String periodo_de_conclusao) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.periodo_de_conclusao = periodo_de_conclusao;
    }

    public Aluno() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPeriodo_de_conclusao() {
        return periodo_de_conclusao;
    }

    public void setPeriodo_de_conclusao(String periodo_de_conclusao) {
        this.periodo_de_conclusao = periodo_de_conclusao;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}