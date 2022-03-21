package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.TCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.TCC;

public interface TCCService {
    TCC criar(TCCDTO dto) throws ApiException;
}
