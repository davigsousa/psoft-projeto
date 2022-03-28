package com.psoft.tccmatch.service;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.model.User;

import java.util.List;

public interface SolicitacaoOrientacaoService {
    SolicitacaoOrientacao solicitarOrientacao(Long idPropostaTCC, User user) throws ApiException;

    List<SolicitacaoOrientacao> listar(User user) throws ApiException;

    SolicitacaoOrientacao findById(Long id) throws ApiException;

    void aprovarSolicitacao(String mensagem, Long idSolicitacao, Object user) throws ApiException;

    void rejeitarSolicitacao(String mensagem, Long idSolicitacao, Object user) throws ApiException;
}
