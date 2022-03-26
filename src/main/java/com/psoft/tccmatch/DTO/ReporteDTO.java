package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.Reporte;

import java.util.ArrayList;
import java.util.List;

public class ReporteDTO {
    private String periodo;
    private String problema;

    public String getPeriodo() {
        return periodo;
    }

    public String getProblema() {
        return problema;
    }

    public static class RespostaAPI {
        public String periodo;
        public String problema;
        public String criador;
        public String orientacao;

        public RespostaAPI(Reporte reporte) {
            this.periodo = reporte.getPeriodo();
            this.problema = reporte.getProblema();
            this.criador = getCriador(reporte);
            this.orientacao = reporte.getOrientacao().toString();
        }

        private String getCriador(Reporte reporte) {
            if (reporte.getAluno() != null) {
                return reporte.getAluno().toString();
            } else {
                return reporte.getProfessor().toString();
            }
        }
    }

    static public class RespostaApiLista {
        public List<ReporteDTO.RespostaAPI> alunos;
        public List<ReporteDTO.RespostaAPI> professores;

        public RespostaApiLista(List<Reporte> alunos, List<Reporte> professores) {
            this.alunos = this.formataLista(alunos);
            this.professores = this.formataLista(professores);
        }

        private List<ReporteDTO.RespostaAPI> formataLista(List<Reporte> list) {
            List<ReporteDTO.RespostaAPI> formatado = new ArrayList<>();
            for (Reporte reporte : list) {
                formatado.add(new ReporteDTO.RespostaAPI(reporte));
            }
            return formatado;
        }
    }
}
