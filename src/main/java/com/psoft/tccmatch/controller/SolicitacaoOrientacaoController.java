package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.SolicitacaoOrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.model.User;
import com.psoft.tccmatch.service.SolicitacaoOrientacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        SolicitacaoOrientacao response = solicitacaoOrientacaoService.solicitarOrientacao(idProposta, (User) user);
        return ResponseEntity.status(201).body(new SolicitacaoOrientacaoDTO.RespostaAPI(response));
    }

    @RequestMapping(path = "/solicitacoes", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> listarSolicitacoes(@RequestAttribute("user") Object user) throws ApiException {
        List<SolicitacaoOrientacao> solicitacoes = solicitacaoOrientacaoService.listar((User) user);

        List<SolicitacaoOrientacaoDTO.RespostaAPI> resultado = solicitacoes.stream()
                .map(SolicitacaoOrientacaoDTO.RespostaAPI::new).collect(Collectors.toList());
        return ResponseEntity.status(200).body(resultado);
    }

    @RequestMapping(path = "/solicitacoes/aprovacoes", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> aprovarSolicitacao(
            @RequestBody RespostaSolicitacaoDTO dto,
            @RequestAttribute("user") Object user
    ) throws ApiException {
        solicitacaoOrientacaoService.aprovarSolicitacao(dto.getMensagem(), dto.getIdSolicitacao(), user);
        return ResponseEntity.status(204).build();
    }

    @RequestMapping(path = "/solicitacoes/{idSolicitacao}/rejeicoes", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> rejeitarSolicitacao(
            @RequestBody RespostaSolicitacaoDTO dto,
            @RequestAttribute("user") Object user
    ) throws ApiException {
        solicitacaoOrientacaoService.rejeitarSolicitacao(dto.getMensagem(), dto.getIdSolicitacao(), user);
        return ResponseEntity.status(204).build();
    }

    public static class RespostaSolicitacaoDTO {
        private String mensagem;
        private Long idSolicitacao;

        public String getMensagem() {
            return mensagem;
        }

        public Long getIdSolicitacao() {
            return idSolicitacao;
        }
    }
}
