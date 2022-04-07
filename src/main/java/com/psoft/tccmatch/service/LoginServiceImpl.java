package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.UserDTO;
import com.psoft.tccmatch.config.JwtTokenUtil;
import com.psoft.tccmatch.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Override
    public String getJwtToken(UserDTO dto) throws ApiException {
        authenticate(dto.getEmail(), dto.getSenha());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(dto.getEmail());

        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticate(String username, String password) throws ApiException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new ApiException("USER_DISABLED", HttpStatus.FORBIDDEN);
        } catch (BadCredentialsException e) {
            throw new ApiException("INVALID_CREDENTIALS", HttpStatus.FORBIDDEN);
        }
    }
}
