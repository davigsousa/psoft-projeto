package com.psoft.tccmatch.processors;

import com.psoft.tccmatch.exception.ApiException;

public interface ManipularAreaEstudoProcessor {
    void selecionarArea(Long areaId, Object user) throws ApiException;
    void desselecionarArea(Long areaId, Object user) throws ApiException;
}
