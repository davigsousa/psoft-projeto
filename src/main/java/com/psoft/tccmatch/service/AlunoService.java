package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;

import java.util.List;

public interface AlunoService {
    Aluno criar(AlunoDTO dto) throws ApiException;

    Aluno editar(AlunoDTO dto) throws ApiException;

    Aluno get(String matricula) throws ApiException;

    Aluno getById(Long id) throws ApiException;

    List<Aluno> getAll();

    void remover(String matricula) throws ApiException;
}
