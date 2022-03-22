package com.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
@Table
public class Orientacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @OneToOne
    private TCC tcc;
    @OneToOne()
    private Professor professor;
    @OneToOne()
    private Aluno aluno;
    @OneToOne()
    private AreaEstudo area;
    private String periodoTCC;
    private Boolean statusAprovacao;
    private Boolean statusFinalizacao;
    
    public Orientacao() {}

    public Orientacao(TCC tcc, Professor professor, Aluno aluno, AreaEstudo area, String periodoTCC, Boolean statusAprovacao) {
        this.tcc = tcc;
        this.professor = professor;
        this.aluno = aluno;
        this.area = area;
        this.periodoTCC = periodoTCC;
        this.statusAprovacao = statusAprovacao;
        this.statusFinalizacao = false;
    }

    public TCC getTcc() {
        return tcc;
    }

    public void setTcc(TCC tcc) {
        this.tcc = tcc;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public AreaEstudo getArea() {
        return area;
    }

    public void setArea(AreaEstudo area) {
        this.area = area;
    }

    public String getPeriodoTCC() {
        return periodoTCC;
    }

    public void setPeriodoTCC(String periodoTCC) {
        this.periodoTCC = periodoTCC;
    }

    public Boolean getStatusAprovacao() {
        return statusAprovacao;
    }

    public void setStatusAprovacao(Boolean statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public Boolean getStatusFinalizacao() {
        return statusFinalizacao;
    }

    public void setStatusFinalizacao(Boolean statusFinalizacao) {
        this.statusFinalizacao = statusFinalizacao;
    }
}
