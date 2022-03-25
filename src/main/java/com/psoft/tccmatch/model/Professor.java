package com.psoft.tccmatch.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Professor implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private int maxOrientandos;
    @ManyToMany()
    @JoinTable(
            name = "professor_area_estudo",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "area_estudo_id")
    )
    private List<AreaEstudo> areasEstudo;
    @OneToMany()
    private List<SolicitacaoOrientacao> solicitacoes;
    @OneToMany()
    private List<PropostaTCC> propostas;
    @OneToMany()
    private List<Orientacao> orientacoes;
    @OneToMany()
    private List<Laboratorio> laboratorios;
    @OneToMany()
    private List<Reporte> reportes;

    public Professor() {
    }

    public Professor(String nome, String email, List<Laboratorio> laboratorios, String senha) {
        this.nome = nome;
        this.email = email;
        this.laboratorios = laboratorios;
        this.senha = senha;
        this.maxOrientandos = 0;
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

    public List<AreaEstudo> getAreasEstudo() {
        return areasEstudo;
    }

    public void setAreasEstudo(List<AreaEstudo> areasEstudo) {
        this.areasEstudo = areasEstudo;
    }

    public void adicionarAreaEstudo(AreaEstudo area) {
        this.areasEstudo.add(area);
    }

    public void removerAreaEstudo(AreaEstudo area) {
        this.areasEstudo.remove(area);
    }

    public List<SolicitacaoOrientacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<SolicitacaoOrientacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    public List<PropostaTCC> getPropostas() {
        return propostas;
    }

    public void setPropostas(List<PropostaTCC> propostas) {
        this.propostas = propostas;
    }

    public List<Orientacao> getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(List<Orientacao> orientacoes) {
        this.orientacoes = orientacoes;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }
}
