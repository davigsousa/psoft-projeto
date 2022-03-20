package com.psoft.tccmatch.service;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;

import java.util.List;

public interface AreaEstudoService {
    AreaEstudo getByLabel(String label) throws ApiException;

    AreaEstudo getById(Long id) throws ApiException;

    AreaEstudo create(String label) throws ApiException;

    List<AreaEstudo> getAll();
}
