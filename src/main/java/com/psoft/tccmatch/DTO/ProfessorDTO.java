package com.psoft.tccmatch.DTO;

import java.util.List;
import java.util.Optional;

public class ProfessorDTO {
    private Optional<Long> id;
    private String nome;
    private String email;
    private List<Long> laboratorios;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Long> getLaboratorios() {
        return laboratorios;
    }

    public Long getId() {
        return this.id.orElse(null);
    }
}
