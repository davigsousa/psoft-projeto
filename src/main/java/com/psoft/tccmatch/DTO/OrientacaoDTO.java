package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Professor;

public class OrientacaoDTO {
    private String theme;
    private Long idProfessor;
    private Long idAluno;
    private Long idAreaInteresse;
    private String periodoTCC;
    private Boolean statusAprovacao;

    public String getTheme() {
        return theme;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public Long getIdAreaInteresse() {
        return idAreaInteresse;
    }

    public String getPeriodoTCC() {
        return periodoTCC;
    }

    public Boolean getStatusAprovacao() {
        return statusAprovacao;
    }
}
