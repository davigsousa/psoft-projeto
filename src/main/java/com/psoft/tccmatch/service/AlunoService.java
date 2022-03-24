package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;

import java.util.List;

public interface AlunoService {
    Aluno criar(AlunoDTO dto) throws ApiException;

    Aluno editar(AlunoDTO dto) throws ApiException;

    Aluno get(String matricula) throws ApiException;

    Aluno getById(Long id) throws ApiException;

    List<Aluno> getAll();

    void remover(String matricula) throws ApiException;

    SolicitacaoOrientacao solicitaOrientacao(OrientacaoDTO dto, Object user) throws ApiException;

    Aluno selecionarArea(String matricula, Long areaID) throws ApiException;

    Aluno desselecionarArea(String matricula, Long areaID) throws ApiException;
}
