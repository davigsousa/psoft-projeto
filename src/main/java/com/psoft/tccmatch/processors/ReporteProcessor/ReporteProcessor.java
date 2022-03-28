package com.psoft.tccmatch.processors.ReporteProcessor;

import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Reporte;
import com.psoft.tccmatch.model.User;

public interface ReporteProcessor {
    Reporte criar(User user, Reporte reporte, Orientacao orientacao, ReporteDTO dto) throws ApiException;
}
