package com.psoft.tccmatch.enviadores;

import org.springframework.stereotype.Service;

@Service
public class CriacaoPropostaTCCEmail implements EnviadorEmail {
    @Override
    public void enviar(String email) {
        System.out.println("NOTIFICAÇÃO: E-mail de criação de proposta de TCC enviado para " + email);
    }
}
