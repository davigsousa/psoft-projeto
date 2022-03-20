package com.psoft.tccmatch.model;

import javax.persistence.*;

@Entity
@Table(name = "AreaEstudo")
public class AreaEstudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String label;

    public AreaEstudo() {
    }

    public AreaEstudo(String label) {
        this.label = label;
    }


    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
