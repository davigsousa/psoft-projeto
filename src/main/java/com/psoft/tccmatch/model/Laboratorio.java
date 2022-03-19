package com.psoft.tccmatch.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Laboratorio")
public class Laboratorio {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String nome;
    @ManyToMany()
    @JoinTable(
            name = "professor_laboratorio",
            joinColumns = @JoinColumn(name = "laboratorio_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores;
    @ManyToMany()
    @JoinTable(
            name = "aluno_laboratorio",
            joinColumns = @JoinColumn(name = "laboratorio_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos;

    private Laboratorio() {
    }

    public Laboratorio(String nome) {
        this.nome = nome;
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

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
