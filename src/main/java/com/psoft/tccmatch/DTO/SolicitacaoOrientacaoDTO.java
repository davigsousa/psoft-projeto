package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;

public class SolicitacaoOrientacaoDTO {
    public static class RespostaAPIAluno {
        public String resposta;
        public Boolean isAprovado;
        public String solicitante;
        public String professor;
        public String proposta;

        public RespostaAPIAluno(SolicitacaoOrientacao solicitacao) {
            this.resposta = solicitacao.getResposta();
            this.isAprovado = solicitacao.getAprovado();
            this.professor = solicitacao.getProfessor().getEmail();
            this.proposta = solicitacao.getProposta().getTitulo();
            this.solicitante = solicitacao.getAluno().toString();
        }
    }
}
