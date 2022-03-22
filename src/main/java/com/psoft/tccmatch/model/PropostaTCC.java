package com.psoft.tccmatch.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class PropostaTCC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String titulo;
    private String descricao;
    private String status;
    @OneToOne()
    private SolicitacaoOrientacao solicitacao;
    @OneToMany()
    private List<AreaEstudo> areasEstudo;

    public PropostaTCC(){
    }

    public PropostaTCC(String titulo, String descricao, String status, List<AreaEstudo> areasEstudo) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AreaEstudo> getAreasEstudo() {
        return areasEstudo;
    }

    public void setAreasEstudo(List<AreaEstudo> areasEstudo) {
        this.areasEstudo = areasEstudo;
    }
}
