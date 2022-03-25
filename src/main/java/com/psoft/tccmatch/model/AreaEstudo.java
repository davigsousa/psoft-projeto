package com.psoft.tccmatch.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AreaEstudo")
public class AreaEstudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String assunto;
    @ManyToMany()
    @JoinTable(
            name = "professor_area_estudo",
            joinColumns = @JoinColumn(name = "area_estudo_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores;

    public AreaEstudo() {
    }

    public AreaEstudo(String assunto) {
        this.assunto = assunto;
    }


    public Long getId() {
        return id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return "AreaEstudo{" +
                "assunto='" + assunto + '\'' +
                '}';
    }
}
