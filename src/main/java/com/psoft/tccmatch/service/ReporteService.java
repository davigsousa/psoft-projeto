package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Reporte;
import com.psoft.tccmatch.model.User;

import java.util.List;

public interface ReporteService {

    Reporte cria(User user, Long orientacao_id, ReporteDTO dto) throws ApiException;

    List<Reporte> buscaPorPeriodoAluno(String periodo);

    List<Reporte> buscaPorPeriodoProfessor(String periodo);

    ReporteDTO.RespostaApiLista buscaPorPeriodo(String periodo);
}
