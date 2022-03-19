package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroProfessor {
    static final String PROFESSOR_EXISTE = "O professor já existe.";
    static final String PROFESSOR_NAO_EXISTE = "O professor não existe.";

    public static ApiException erroProfessorJaExiste() {
        return new ApiException(ErroProfessor.PROFESSOR_EXISTE,
                HttpStatus.BAD_REQUEST);
    }

    public static ApiException erroProfessorNaoExiste() {
        return new ApiException(ErroProfessor.PROFESSOR_NAO_EXISTE,
                HttpStatus.NOT_FOUND);
    }
}
