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
    private PropostaTCC propostaTcc;
    @OneToOne()
    private Professor professor;
    @OneToOne()
    private Aluno aluno;
    @OneToOne()
    private AreaEstudo area;
    private String periodoInicio;
    private String periodoFim;

    public Orientacao() {}

    public Orientacao(PropostaTCC propostaTcc, Professor professor, Aluno aluno, AreaEstudo area, String periodoInicio) {
        this.propostaTcc = propostaTcc;
        this.professor = professor;
        this.aluno = aluno;
        this.area = area;
        this.periodoInicio = periodoInicio;
        this.periodoFim = null;
    }

    public PropostaTCC getTcc() {
        return propostaTcc;
    }

    public void setTcc(PropostaTCC propostaTcc) {
        this.propostaTcc = propostaTcc;
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

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public String getPeriodoFim() {
        return periodoFim;
    }

    public void setPeriodoFim(String periodoFim) {
        this.periodoFim = periodoFim;
    }

}
