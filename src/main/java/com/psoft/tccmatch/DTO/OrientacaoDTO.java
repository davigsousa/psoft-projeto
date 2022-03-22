package com.psoft.tccmatch.DTO;

public class OrientacaoDTO {
    private Long idThemeTCC;
    private Long idProfessor;
    private Long idAluno;
    private Long idAreaInteresse;
    private String periodoTCC;
    private Boolean statusAprovacao;

    public Long getIdThemeTCC() {
        return idThemeTCC;
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
