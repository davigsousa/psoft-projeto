package com.psoft.tccmatch.model;

import javax.persistence.*;
import java.util.List;

public class TCC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean status;
    @OneToMany()
    private List<AreaEstudo> areasEstudo;

    public TCC(){
    }

    public TCC(String titulo, String descricao, Boolean status, List<AreaEstudo> areasEstudo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.areasEstudo = areasEstudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<AreaEstudo> getAreasEstudo() {
        return areasEstudo;
    }

    public void setAreasEstudo(List<AreaEstudo> areasEstudo) {
        this.areasEstudo = areasEstudo;
    }
}
