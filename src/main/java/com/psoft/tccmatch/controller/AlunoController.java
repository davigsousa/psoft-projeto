package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.service.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spi.service.contexts.SecurityContext;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class AlunoController {
    @Autowired
    private AlunoServiceImpl alunoServiceImpl;

    @RequestMapping(path = "/alunos", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ResponseEntity<?> criarAluno(@RequestBody AlunoDTO alunoDTO) throws ApiException {
        Aluno result = alunoServiceImpl.criar(alunoDTO);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/alunos", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAlunos() {
        List<Aluno> result = alunoServiceImpl.getAll();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/alunos/{matricula}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAluno(@PathVariable("matricula") String matricula) throws ApiException {
        Aluno result = alunoServiceImpl.get(matricula);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/alunos", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> editarAluno(@RequestBody AlunoDTO alunoDTO) throws ApiException {
        Aluno result = alunoServiceImpl.editar(alunoDTO);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/alunos/{matricula}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletarAluno(@PathVariable("matricula") String matricula) throws ApiException {
        alunoServiceImpl.remover(matricula);
        return (ResponseEntity<?>) ResponseEntity.status(200);
    }

    @RequestMapping(path = "/aluno/orientacao", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> solicitarOrientacao(
            @RequestBody OrientacaoDTO dto,
            @RequestAttribute("user_id") Object user
    ) throws ApiException {
        SolicitacaoOrientacao response = alunoServiceImpl.solicitaOrientacao(dto, user);
        return ResponseEntity.status(201).body(response);
    }
}