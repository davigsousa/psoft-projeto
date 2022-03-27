package com.psoft.tccmatch.DTO;

import java.util.List;

public class CriacaoProfessorDTO {
    private String nome;
    private String email;
    private List<Long> laboratorios;
    private String senha;

    public String getSenha() {
        return senha;
    }

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