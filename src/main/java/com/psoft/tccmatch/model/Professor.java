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
    @OneToMany()
    private List<Laboratorio> laboratorios;

    public Professor() {
    }

    public Professor(String nome, String email, List<Laboratorio> laboratorios) {
        this.nome = nome;
        this.email = email;
        this.laboratorios = laboratorios;
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
}
