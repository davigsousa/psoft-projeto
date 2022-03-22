package com.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
public class SolicitacaoOrientacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String resposta;
    private Boolean isAprovado;
    private String solicitante;

    public SolicitacaoOrientacao(String resposta, Boolean isAprovado, String solicitante) {
        this.resposta = resposta;
        this.isAprovado = isAprovado;
        this.solicitante = solicitante;
    }

    public Long getId() {
        return id;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Boolean getAprovado() {
        return isAprovado;
    }

    public void setAprovado(Boolean aprovado) {
        isAprovado = aprovado;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
}
