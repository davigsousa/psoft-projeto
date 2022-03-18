package com.psoft.tccmatch.model;

import javax.persistence.*;
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
    private Set<Professor> professores;
    @ManyToMany()
    @JoinTable(
            name = "aluno_laboratorio",
            joinColumns = @JoinColumn(name = "laboratorio_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private Set<Aluno> alunos;

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

    public Set<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(Set<Professor> professores) {
        this.professores = professores;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
}
