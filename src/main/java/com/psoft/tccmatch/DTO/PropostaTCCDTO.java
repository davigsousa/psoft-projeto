package com.psoft.tccmatch.DTO;

import java.util.List;

public class PropostaTCCDTO {
    private String titulo;
    private String descricao;
    private String status;
    private List<Long> areasEstudo;

    public String getTitulo() { return titulo; }

    public String getDescricao() { return descricao; }

    public String getStatus() { return status; }

    public List<Long> getAreasEstudo() { return areasEstudo; }
}
