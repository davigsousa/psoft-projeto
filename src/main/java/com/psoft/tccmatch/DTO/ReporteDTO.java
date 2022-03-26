package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.Reporte;

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
        public String usuario;
        public String orientacao;

        public RespostaAPI(Reporte reporte) {
            this.periodo = reporte.getPeriodo();
            this.problema = reporte.getProblema();
            this.usuario = reporte.getUsuario();
            this.orientacao = reporte.getOrientacao().toString();
        }
    }
}
