package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Professor;

import java.util.List;

public interface OrientacaoService {
    Orientacao create(OrientacaoDTO dto) throws ApiException;

    Orientacao update(OrientacaoDTO dto) throws ApiException;

    Orientacao get(String tema) throws ApiException;

    List<Orientacao> getAllActive();

    List<Orientacao> getAllActive(Professor professor);

    List<Orientacao> getAllByProfessor(Professor professor);

    void remove(String tema) throws ApiException;
}
