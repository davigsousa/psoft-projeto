package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroAreaEstudo {
    static final String AREA_EXISTE = "A área de estudo já existe.";
    static final String AREA_NAO_EXISTE = "A área de estudo não existe.";

    public static ApiException erroAreaNaoExiste() {
        return new ApiException(ErroAreaEstudo.AREA_NAO_EXISTE,
                HttpStatus.NOT_FOUND);
    }

    public static ApiException erroAreaJaExiste() {
        return new ApiException(ErroAreaEstudo.AREA_EXISTE,
                HttpStatus.BAD_REQUEST);
    }

}
