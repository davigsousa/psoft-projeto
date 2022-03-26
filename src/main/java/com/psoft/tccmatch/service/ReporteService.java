package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Reporte;

import java.util.List;

public interface ReporteService {

    Reporte cria(Object user, Long orientacao_id, ReporteDTO dto) throws ApiException;

    List<Reporte> list();
}
