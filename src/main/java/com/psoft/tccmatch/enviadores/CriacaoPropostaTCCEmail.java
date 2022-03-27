package com.psoft.tccmatch.enviadores;

import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriacaoPropostaTCCEmail implements EnviadorEmail {
    @Override
    public void enviar(String email) {
        System.out.println("NOTIFICAÇÃO: E-mail de criação de proposta de TCC enviado para " + email);
    }
}
