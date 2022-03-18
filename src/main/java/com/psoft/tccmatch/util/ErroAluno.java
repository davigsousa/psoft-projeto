package com.psoft.tccmatch.util;

import com.psoft.tccmatch.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ErroAluno {
    static final String ALUNO_EXISTE = "O aluno já existe.";
    static final String ALUNO_NAO_EXISTE = "O aluno não existe.";

    public static ApiException erroAlunoJaExiste() {
        return new ApiException(ErroAluno.ALUNO_EXISTE,
                HttpStatus.BAD_REQUEST);
    }

    public static ApiException erroAlunoNaoExiste() {
        return new ApiException(ErroAluno.ALUNO_NAO_EXISTE,
                HttpStatus.BAD_REQUEST);
    }
}
