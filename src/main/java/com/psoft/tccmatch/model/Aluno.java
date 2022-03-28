package com.psoft.tccmatch.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "aluno")
public class Aluno implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private String senha;
    private String periodoDeConclusao;
    @ManyToMany()
    @JoinTable(
            name = "aluno_area_estudo",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "area_estudo_id")
    )
    private List<AreaEstudo> areasEstudo;
    @OneToMany()
    private List<Reporte> reportes;
    @OneToOne()
    private SolicitacaoOrientacao solicitacao;
    @OneToOne()
    private PropostaTCC proposta;
    @OneToOne()
    private Orientacao orientacao;

    private Aluno() {}

    public Aluno(String nome, String matricula, String email, String senha, String periodoDeConclusao) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.senha = senha;
        this.periodoDeConclusao = periodoDeConclusao;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPeriodoDeConclusao() {
        return periodoDeConclusao;
    }

    public void setPeriodoDeConclusao(String periodoDeConclusao) {
        this.periodoDeConclusao = periodoDeConclusao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Tipo getTipo() {
        return Tipo.ALUNO;
    }

    @Override
    public String toString() {
        return this.matricula + " - " + this.nome + " - " + this.email;
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

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public SolicitacaoOrientacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(SolicitacaoOrientacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public PropostaTCC getProposta() {
        return proposta;
    }

    public void setProposta(PropostaTCC proposta) {
        this.proposta = proposta;
    }

    public Orientacao getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }
}