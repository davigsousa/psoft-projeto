package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroReporte {
    static final String REPORTE_EXISTE = "O reporte já existe.";

    public static ApiException erroReporteJaExiste() {
        return new ApiException(ErroReporte.REPORTE_EXISTE,
                HttpStatus.BAD_REQUEST);
    }
}
