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

    public Reporte(String periodo, String problema) {
        this.periodo = periodo;
        this.problema = problema;
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

}
