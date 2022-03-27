package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.SolicitacaoOrientacao;

public class SolicitacaoOrientacaoDTO {
    public static class RespostaAPI {
        public String resposta;
        public Boolean isAprovado;
        public String solicitante;
        public String professor;
        public String proposta;

        public RespostaAPI(SolicitacaoOrientacao solicitacao) {
            this.resposta = solicitacao.getResposta();
            this.isAprovado = solicitacao.getAprovado();
            this.professor = solicitacao.getProfessor().getEmail();
            this.proposta = solicitacao.getProposta().getTitulo();

            if (solicitacao.getSolicitante().equals("PROFESSOR")) {
                this.solicitante = solicitacao.getProfessor().toString();
            } else {
                this.solicitante = solicitacao.getAluno().toString();
            }
        }
    }
}
