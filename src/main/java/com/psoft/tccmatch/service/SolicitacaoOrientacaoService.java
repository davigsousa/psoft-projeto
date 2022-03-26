package com.psoft.tccmatch.service;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;

import java.util.List;

public interface SolicitacaoOrientacaoService {
    SolicitacaoOrientacao solicitarOrientacao(Long idPropostaTCC, Object user) throws ApiException;

    List<SolicitacaoOrientacao> listar(Object user) throws ApiException;
}
