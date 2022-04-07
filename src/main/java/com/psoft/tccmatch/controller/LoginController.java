package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.UserDTO;
import com.psoft.tccmatch.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO dto) throws Exception {
        String token = loginService.getJwtToken(dto);
        return ResponseEntity.ok(new JwtResponse(token));
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
