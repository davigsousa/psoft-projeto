package com.psoft.tccmatch.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Orientacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String periodoInicio;
    private String periodoFim;
    @OneToOne
    private PropostaTCC propostaTcc;
    @OneToOne()
    private Professor professor;
    @OneToOne()
    private Aluno aluno;
    @OneToMany
    private Set<Reporte> reportes;


    public Orientacao() {}

    public Orientacao(PropostaTCC propostaTcc, Professor professor, Aluno aluno, String periodoInicio) {
        this.propostaTcc = propostaTcc;
        this.professor = professor;
        this.aluno = aluno;
        this.periodoInicio = periodoInicio;
        this.periodoFim = null;
    }

    public Long getId() {
        return id;
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

    public PropostaTCC getPropostaTcc() {
        return propostaTcc;
    }

    public void setPropostaTcc(PropostaTCC propostaTcc) {
        this.propostaTcc = propostaTcc;
    }

    public Set<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(Set<Reporte> reportes) {
        this.reportes = reportes;
    }
}
