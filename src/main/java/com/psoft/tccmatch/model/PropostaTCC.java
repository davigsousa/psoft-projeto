package com.psoft.tccmatch.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PropostaTCC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String titulo;
    private String descricao;
    private String status;
    @OneToOne
    private Aluno aluno;
    @OneToOne()
    private Professor professor;
    @OneToOne()
    private SolicitacaoOrientacao solicitacao;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "areas_proposta",
            joinColumns = @JoinColumn(name = "proposta_id"),
            inverseJoinColumns = @JoinColumn(name = "area_estudo_id")
    )
    private Set<AreaEstudo> areasEstudo = new HashSet<>();

    public PropostaTCC(){
    }

    public PropostaTCC(String titulo, String descricao, String status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
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

    public Set<AreaEstudo> getAreasEstudo() {
        return areasEstudo;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setSolicitacao(SolicitacaoOrientacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public SolicitacaoOrientacao getSolicitacao() {
        return solicitacao;
    }

    public void addAreaEstudo(AreaEstudo area) {
        this.areasEstudo.add(area);
    }
}
