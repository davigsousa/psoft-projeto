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
}
