package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Orientacao;

import java.util.List;

public interface OrientacaoService {
    Orientacao create(OrientacaoDTO dto) throws ApiException;

    Orientacao update(OrientacaoDTO dto) throws ApiException;

    Orientacao get(String tema) throws ApiException;

    List<Orientacao> getAll();

    void remove(String tema) throws ApiException;
}
