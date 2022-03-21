package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroTCC {
    static final String TCC_EXISTE = "O tema de TCC já existe.";
    static final String TCC_NAO_EXISTE = "O tema de TCC não existe.";
    static final String TCC_CONTER_AREA_ESTUDO = "O tema deve conter pelo menos uma área de estudo";

    public static ApiException erroTCCJaExiste() {
        return new ApiException(ErroTCC.TCC_EXISTE,
                HttpStatus.BAD_REQUEST);
    }

    public static ApiException erroTCCNaoExiste() {
        return new ApiException(ErroTCC.TCC_NAO_EXISTE,
                HttpStatus.NOT_FOUND);
    }

    public static ApiException erroTCCDeveTerAreaDeEstudo() {
        return new ApiException(ErroTCC.TCC_CONTER_AREA_ESTUDO,
                HttpStatus.NOT_FOUND);
    }

}
