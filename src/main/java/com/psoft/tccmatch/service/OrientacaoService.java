package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Professor;

import java.util.List;

public interface OrientacaoService {
    Orientacao create(OrientacaoDTO dto) throws ApiException;

    List<Orientacao> getAllActive();

    List<Orientacao> getAllActive(Professor professor);

    List<Orientacao> getAllByProfessor(Professor professor);

    OrientacaoDTO.RespostaApiLista getAllByPeriodo(String periodo);

    Orientacao getById(Long id) throws ApiException;

    List<Orientacao> getAllEmCurso();

    List<Orientacao> getAllByPeriodoFinalizadas(String periodo);

    void finalizarOrientacao(Long idOrientacao, String periodoFim) throws ApiException;

    OrientacaoDTO.RespostaApiLista buscaPorPeriodo(String periodo);
}
