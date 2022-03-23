package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.UserDTO;
import com.psoft.tccmatch.exception.ApiException;

public interface LoginService {
    String getJwtToken(UserDTO dto) throws ApiException;
}
