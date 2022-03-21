package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.AreaEstudo;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.Optional;

public class TCCDTO {
    private String titulo;
    private String descricao;
    private Boolean status;
    private List<AreaEstudo> areasEstudo;

    public String getTitulo() { return titulo; }

    public String getDescricao() { return descricao; }

    public Boolean getStatus() { return status; }

    public List<AreaEstudo> getAreasEstudo() { return areasEstudo; }
}
