package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroOrientacao {
    static final String ORIENTACAO_EXISTE = "A orientação já existe.";
    static final String ORIENTACAO_NAO_EXISTE = "A orientação não existe.";

    public static ApiException erroOrientacaoJaExiste() {
        return new ApiException(ErroOrientacao.ORIENTACAO_EXISTE,
                HttpStatus.BAD_REQUEST);
    }

    public static ApiException erroOrientacaoNaoExiste() {
        return new ApiException(ErroOrientacao.ORIENTACAO_NAO_EXISTE,
                HttpStatus.BAD_REQUEST);
    }
}
