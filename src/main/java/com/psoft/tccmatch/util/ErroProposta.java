package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroProposta {
    static final String PROFESSOR_OU_ALUNO_NAO_EXISTE = "Professor ou aluno não existe.";

    public static ApiException erroProposta() {
        return new ApiException(ErroProposta.PROFESSOR_OU_ALUNO_NAO_EXISTE,
                HttpStatus.NOT_FOUND);
    }
}
