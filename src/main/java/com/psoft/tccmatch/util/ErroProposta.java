package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroProposta {
    static final String PROFESSOR_OU_ALUNO_NAO_EXISTE = "Professor ou aluno não existe.";
    static final String PROPOSTA_NAO_DISPONIVEL = "Proposta não está disponível.";


    public static ApiException erroProposta() {
        return new ApiException(ErroProposta.PROFESSOR_OU_ALUNO_NAO_EXISTE,
                HttpStatus.NOT_FOUND);
    }

    public static ApiException erroPropostaNaoDisponivel() {
        return new ApiException(ErroProposta.PROPOSTA_NAO_DISPONIVEL,
                HttpStatus.BAD_REQUEST);
    }
}
