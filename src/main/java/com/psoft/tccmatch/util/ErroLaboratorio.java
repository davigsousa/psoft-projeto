package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroLaboratorio {
    static final String LAB_NAO_EXISTE = "O laboratorio não existe.";

    public static ApiException erroLabNaoExiste() {
        return new ApiException(ErroLaboratorio.LAB_NAO_EXISTE,
                HttpStatus.NOT_FOUND);
    }
}
