package com.psoft.tccmatch.service;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;

public interface SolicitacaoOrientacaoService {
    SolicitacaoOrientacao solicitarOrientacao(Long idPropostaTCC, Object user) throws ApiException;
}
