package com.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
@Table(name = "AreaEstudo")
public class AreaEstudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String assunto;

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
}
