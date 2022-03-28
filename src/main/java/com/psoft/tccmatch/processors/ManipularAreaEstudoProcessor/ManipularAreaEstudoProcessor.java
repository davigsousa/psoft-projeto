package com.psoft.tccmatch.processors.ManipularAreaEstudoProcessor;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.User;

public interface ManipularAreaEstudoProcessor {
    void selecionarArea(AreaEstudo area, User user) throws ApiException;
    void desselecionarArea(AreaEstudo area, User user) throws ApiException;
}
