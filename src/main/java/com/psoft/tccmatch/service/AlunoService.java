package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.model.Aluno;

public interface AlunoService {
    public Aluno criar(AlunoDTO dto) throws Exception;

    public void editar(AlunoDTO dto);

    public Aluno get(String matricula) throws Exception;
}
