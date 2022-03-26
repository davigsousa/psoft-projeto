package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.SolicitacaoOrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.service.SolicitacaoOrientacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SolicitacaoOrientacaoController {
    @Autowired
    private SolicitacaoOrientacaoService solicitacaoOrientacaoService;

    @RequestMapping(path = "/solicitacoes/{idProposta}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> solicitarOrientacao(
            @PathVariable("idProposta") Long idProposta,
            @RequestAttribute("user") Object user
    ) throws ApiException {
        SolicitacaoOrientacao response = solicitacaoOrientacaoService.solicitarOrientacao(idProposta, user);
        return ResponseEntity.status(201).body(new SolicitacaoOrientacaoDTO.RespostaAPIAluno(response));
    }

    @RequestMapping(path = "/solicitacoes", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> listarSolicitacoes(@RequestAttribute("user") Object user) throws ApiException {
        List<SolicitacaoOrientacao> solicitacoes = solicitacaoOrientacaoService.listar(user);
        return ResponseEntity.status(200).body(solicitacoes);
    }

}
