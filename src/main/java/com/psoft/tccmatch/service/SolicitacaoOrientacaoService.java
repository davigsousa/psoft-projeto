package com.psoft.tccmatch.service;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;

import java.util.List;

public interface SolicitacaoOrientacaoService {
    SolicitacaoOrientacao solicitarOrientacao(Long idPropostaTCC, Object user) throws ApiException;

    List<SolicitacaoOrientacao> listar(Object user) throws ApiException;

    SolicitacaoOrientacao findById(Long id) throws ApiException;

    void aprovarSolicitacao(String mensagem, Long idSolicitacao, Object user) throws ApiException;

    void rejeitarSolicitacao(String mensagem, Long idSolicitacao, Object user) throws ApiException;
}
