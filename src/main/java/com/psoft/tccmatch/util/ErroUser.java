package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroUser {
    static final String TIPO_USUARIO = "O usuário deveria ser aluno ou professor.";

    public static ApiException erroTipoUsuario() {
        return new ApiException(ErroUser.TIPO_USUARIO,
                HttpStatus.BAD_REQUEST);
    }
}
