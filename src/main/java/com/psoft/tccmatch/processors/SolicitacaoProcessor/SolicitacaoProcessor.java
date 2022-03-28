package com.psoft.tccmatch.processors.SolicitacaoProcessor;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.model.User;

import java.util.List;

public interface SolicitacaoProcessor {
    SolicitacaoOrientacao criar(PropostaTCC propostaTCC, User user) throws ApiException;
    List<SolicitacaoOrientacao> listar(User user) throws ApiException;
}
