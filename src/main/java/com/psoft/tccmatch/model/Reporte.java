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
    private String usuario;
    @ManyToOne
    private Orientacao orientacao;

    public Reporte() {
    }

    public Reporte(String periodo, String problema, Orientacao orientacao, String usuario) {
        this.periodo = periodo;
        this.problema = problema;
        this.orientacao = orientacao;
        this.usuario = usuario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
