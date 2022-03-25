package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.Professor;

import java.util.List;
import java.util.Optional;

public class ProfessorDTO {
    private Optional<Long> id;
    private String nome;
    private String email;
    private List<Long> laboratorios;
    private String senha;
    private Optional<Integer> maxOrientandos;

    public String getSenha() {
        return senha;
    }

    public Optional<Integer> getMaxOrientandos() {
        return maxOrientandos;
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

    public Long getId() {
        return this.id.orElse(null);
    }

    public static class RespostaApi {
        final public Long id;
        final public String nome;
        final public String email;
        final public List<String> laboratorios;
        final public int maxOrientandos;
        public List<AreaEstudo> areasEstudo;

        public RespostaApi(Professor professor) {
            this.id = professor.getId();
            this.nome = professor.getNome();
            this.email = professor.getEmail();
            this.laboratorios = professor.getLaboratorios();
            this.maxOrientandos = professor.getMaxOrientandos();
            this.areasEstudo = professor.getAreasEstudo();
        }
    }
}
