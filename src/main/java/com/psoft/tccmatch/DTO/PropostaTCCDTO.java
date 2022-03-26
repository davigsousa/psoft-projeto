package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;

import java.util.List;
import java.util.Set;

public class PropostaTCCDTO {
    private String titulo;
    private String descricao;
    private String status;
    private List<Long> areasEstudo;

    public String getTitulo() { return titulo; }

    public String getDescricao() { return descricao; }

    public String getStatus() { return status; }

    public List<Long> getAreasEstudo() { return areasEstudo; }

    public static class RespostaAPI {
        public String titulo;
        public String descricao;
        public String status;
        public String criador;
        public Set<AreaEstudo> areas_de_estudo;
        public SolicitacaoOrientacao solicitacao;

        public RespostaAPI(PropostaTCC proposta) {
            this.titulo = proposta.getTitulo();
            this.descricao = proposta.getDescricao();
            this.status = proposta.getStatus();
            this.areas_de_estudo = proposta.getAreasEstudo();
            this.solicitacao = proposta.getSolicitacao();
            this.criador = getSolicitante(proposta);
        }

        private String getSolicitante(PropostaTCC proposta) {
            if (proposta.getProfessor() != null) {
                return proposta.getProfessor().getNome();
            } else {
                return proposta.getAluno().getNome();
            }
        }
    }
}
