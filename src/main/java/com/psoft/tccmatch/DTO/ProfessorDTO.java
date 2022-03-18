package com.psoft.tccmatch.DTO;

import java.util.List;

public class ProfessorDTO {
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
}
