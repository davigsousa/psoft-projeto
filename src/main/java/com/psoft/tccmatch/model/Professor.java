package com.psoft.tccmatch.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private int maxOrientandos;
    @OneToMany()
    private List<Laboratorio> laboratorios;
    @ManyToMany()
    @JoinTable(
            name = "professor_area_estudo",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "area_estudo_id")
    )
    private List<AreaEstudo> areasEstudo;
    @OneToMany()
    private List<SolicitacaoOrientacao> solicitacoes;

    public Professor() {
    }

    public Professor(String nome, String email, List<Laboratorio> laboratorios, String senha, int maxOrientandos) {
        this.nome = nome;
        this.email = email;
        this.laboratorios = laboratorios;
        this.senha = senha;
        this.maxOrientandos = maxOrientandos;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getLaboratorios() {
        List<String> labs = new ArrayList<>();
        for (Laboratorio lab : this.laboratorios) {
            labs.add(lab.getNome());
        }
        return labs;
    }

    public List<Laboratorio> laboratoriosEntities() {
        return this.laboratorios;
    }

    public void setLaboratorios(List<Laboratorio> laboratorios) {
        this.laboratorios = laboratorios;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getMaxOrientandos() {
        return maxOrientandos;
    }

    public void setMaxOrientandos(int maxOrientandos) {
        this.maxOrientandos = maxOrientandos;
    }
}
