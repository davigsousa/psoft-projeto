package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.service.OrientacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrientacaoController {

    @Autowired
    private OrientacaoService orientacaoService;

    @RequestMapping(path = "/orientacoes", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody OrientacaoDTO orientacaoDTO) throws ApiException {
        Orientacao orientacao = orientacaoService.create(orientacaoDTO);

        return ResponseEntity.ok(new OrientacaoDTO.RespostaAPI(orientacao));
    }

    @RequestMapping(path = "/orientacoes", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<?> listar(@RequestAttribute("user") Object user) {
        List<Orientacao> orientacoes = orientacaoService.getAllByProfessor((Professor) user);

        return ResponseEntity.ok(orientacoes);
    }

    @RequestMapping(path = "/orientacoes/{periodo}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> listarPorPeriodo(@PathVariable("periodo") String periodo) {
        OrientacaoDTO.RespostaApiLista orientacoes = orientacaoService.getAllByPeriodo(periodo);

        return ResponseEntity.ok(orientacoes);
    }

    @RequestMapping(path = "/orientacoes/{idOrientacao}/finalizacoes/{periodoFim}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> finalizarOrientacao(
        @PathVariable("idOrientacao") Long idOrientacao,
        @PathVariable("periodoFim") String periodoFim
    ) throws ApiException {
        orientacaoService.finalizarOrientacao(idOrientacao, periodoFim);

        return ResponseEntity.status(201).build();
    }

    @RequestMapping(path = "/orientacoes/relatorio", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> relatorioOrientacoes(@RequestParam(value = "periodo") String periodo) {
        OrientacaoDTO.RespostaApiLista resposta = orientacaoService.buscaPorPeriodo(periodo);
        return ResponseEntity.ok(resposta);
    }
}
