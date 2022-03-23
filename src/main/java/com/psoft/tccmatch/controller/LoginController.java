package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.UserDTO;
import com.psoft.tccmatch.config.JwtTokenUtil;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO dto) throws Exception {

        authenticate(dto.getEmail(), dto.getSenha());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(dto.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new ApiException("USER_DISABLED", HttpStatus.FORBIDDEN);
        } catch (BadCredentialsException e) {
            throw new ApiException("INVALID_CREDENTIALS", HttpStatus.FORBIDDEN);
        }
    }

    private static class JwtResponse {
        private final String token;

        JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public String getMessage() {
            return "Usuário autenticado com sucesso!";
        }
    }
}
