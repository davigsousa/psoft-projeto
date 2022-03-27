package com.psoft.tccmatch.enviadores;

import org.springframework.stereotype.Service;

@Service
public class AlunoSolicitarOrientacaoEmail implements EnviadorEmail {
    @Override
    public void enviar(String email) {
        System.out.println("NOTIFICAÇÃO: E-mail de solicitação de orientação enviado para " + email);
    }
}
