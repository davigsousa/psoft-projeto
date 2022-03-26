package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.DTO.SolicitacaoOrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.service.AlunoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @RequestMapping(path = "/alunos", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ResponseEntity<?> criarAluno(@RequestBody AlunoDTO alunoDTO) throws ApiException {
        Aluno result = alunoService.criar(alunoDTO);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/alunos", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAlunos() {
        List<Aluno> result = alunoService.getAll();

        List<AlunoDTO.RespostaApi> resultList = result.stream().map(AlunoDTO.RespostaApi::new).collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

    @RequestMapping(path = "/alunos/{matricula}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAluno(@PathVariable("matricula") String matricula) throws ApiException {
        Aluno result = alunoService.get(matricula);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/alunos", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> editarAluno(@RequestBody AlunoDTO alunoDTO) throws ApiException {
        Aluno result = alunoService.editar(alunoDTO);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/alunos/{matricula}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletarAluno(@PathVariable("matricula") String matricula) throws ApiException {
        alunoService.remover(matricula);
        return (ResponseEntity<?>) ResponseEntity.status(204);
    }

    @RequestMapping(path = "/alunos/{matricula}/area-estudo/{areaId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> selecionarAreaEstudo(@PathVariable("matricula") String matricula, @PathVariable("areaId") Long areaId) throws ApiException {
        Aluno result = alunoService.selecionarArea(matricula, areaId);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/alunos/{matricula}/area-estudo/{areaId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> desselecionarAreaEstudo(@PathVariable("matricula") String matricula, @PathVariable("areaId") Long areaId) throws ApiException {
        Aluno result = alunoService.desselecionarArea(matricula, areaId);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/aluno/solicitar/{idOrientacao}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> solicitarOrientacao(
            @PathVariable("idOrientacao") Long idOrientacao,
            @RequestAttribute("user") Object user
    ) throws ApiException {
        SolicitacaoOrientacao response = alunoService.solicitaOrientacao(idOrientacao, user);
        return ResponseEntity.status(201).body(new SolicitacaoOrientacaoDTO.RespostaAPIAluno(response));
    }
}