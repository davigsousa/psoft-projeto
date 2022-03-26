package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.*;

import java.util.ArrayList;
import java.util.List;

public class OrientacaoDTO {
    private Long idPropostaTCC;
    private Long idProfessor;
    private Long idAluno;
    private String periodoInicio;

    public Long getIdPropostaTCC() {
        return idPropostaTCC;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public static class RespostaAPI {
        public PropostaTCC propostaTCC;
        public Professor professor;
        public Aluno aluno;
        public String periodoInicio;
        public String periodoFim;

        public RespostaAPI(Orientacao orientacao) {
            this.propostaTCC = orientacao.getPropostaTcc();
            this.professor = orientacao.getProfessor();
            this.aluno = orientacao.getAluno();
            this.periodoInicio = orientacao.getPeriodoInicio();
            this.periodoFim = orientacao.getPeriodoFim();
        }
    }

    static public class RespostaApiLista {
        public List<OrientacaoDTO.RespostaAPI> emCurso;
        public List<OrientacaoDTO.RespostaAPI> finalizadas;

        public RespostaApiLista(List<Orientacao> emCurso, List<Orientacao> finalizadas) {
            this.emCurso = this.formataLista(emCurso);
            this.finalizadas = this.formataLista(finalizadas);
        }

        private List<OrientacaoDTO.RespostaAPI> formataLista(List<Orientacao> list) {
            List<OrientacaoDTO.RespostaAPI> formatado = new ArrayList<>();
            for (Orientacao orientacao : list) {
                formatado.add(new OrientacaoDTO.RespostaAPI(orientacao));
            }
            return formatado;
        }
    }
}
