package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroUser {
    static final String TIPO_USUARIO = "O usuário deveria ser aluno ou professor.";
    static final String SEM_SOLICITACOES = "Não existem solicitações para esse usuário.";

    public static ApiException erroTipoUsuario() {
        return new ApiException(ErroUser.TIPO_USUARIO,
                HttpStatus.BAD_REQUEST);
    }

    public static ApiException erroSemSolicitacoes() {
        return new ApiException(ErroUser.SEM_SOLICITACOES,
                HttpStatus.BAD_REQUEST);
    }
}
