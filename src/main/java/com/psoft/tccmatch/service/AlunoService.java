package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.model.Aluno;

import java.util.List;

public interface AlunoService {
    Aluno criar(AlunoDTO dto) throws Exception;

    void editar(AlunoDTO dto);

    Aluno get(String matricula) throws Exception;

    List<Aluno> getAll();
}
