package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroSolicitacao {
    static final String SEM_SOLICITACOES = "Não existem solicitações para esse usuário.";
    static final String NAO_ENCONTRADA = "Essa solicitação não existe.";

    public static ApiException erroSemSolicitacoes() {
        return new ApiException(ErroSolicitacao.SEM_SOLICITACOES,
                HttpStatus.BAD_REQUEST);
    }

    public static ApiException erroSolicitacaoNaoExiste() {
        return new ApiException(ErroSolicitacao.NAO_ENCONTRADA,
                HttpStatus.BAD_REQUEST);
    }
}
