package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.TCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.TCC;

import java.util.List;

public interface TCCService {
    TCC criar(TCCDTO dto) throws ApiException;

    List<TCC> getAll();
}
