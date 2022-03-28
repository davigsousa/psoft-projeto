package com.psoft.tccmatch.processors.AreaEstudoProcessor;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.User;

public interface AreaEstudoProcessor {
    void selecionarArea(AreaEstudo area, User user) throws ApiException;
    void desselecionarArea(AreaEstudo area, User user) throws ApiException;
}
