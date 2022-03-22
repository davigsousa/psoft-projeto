package com.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private String senha;
    private String periodoDeConclusao;

    private Aluno() {}

    public Aluno(String nome, String matricula, String email, String senha, String periodoDeConclusao) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.senha = senha;
        this.periodoDeConclusao = periodoDeConclusao;
    }

    public Long getId() {
        return id;
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

    public String getPeriodoDeConclusao() {
        return periodoDeConclusao;
    }

    public void setPeriodoDeConclusao(String periodoDeConclusao) {
        this.periodoDeConclusao = periodoDeConclusao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return this.matricula + " - " + this.nome + " - " + this.email;
    }
}