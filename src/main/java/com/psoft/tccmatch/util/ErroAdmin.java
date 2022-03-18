package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroAdmin {
    static final String EXISTE_USUARIO = "Já existe um usuário com esse e-mail.";

    public static ApiException erroUsuarioJaExiste() {
        return new ApiException(ErroAdmin.EXISTE_USUARIO,
                HttpStatus.BAD_REQUEST);
    }
}
