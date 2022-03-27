package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        return ResponseEntity.status(204).build();
    }

    @RequestMapping(path = "/alunos/areas-estudo/{areaId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> selecionarAreaEstudo(@RequestAttribute(value = "user") Object user, @PathVariable("areaId") Long areaId) throws ApiException {
        Aluno aluno = (Aluno) user;
        Aluno result = alunoService.selecionarArea(aluno.getMatricula(), areaId);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/alunos/areas-estudo/{areaId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> desselecionarAreaEstudo(@RequestAttribute(value = "user") Object user, @PathVariable("areaId") Long areaId) throws ApiException {
        Aluno aluno = (Aluno) user;
        Aluno result = alunoService.desselecionarArea(aluno.getMatricula(), areaId);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/alunos/professores-disponiveis", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> getProfessoresDisponiveis(@RequestAttribute(value = "user") Object user) throws ApiException {
        Aluno aluno = (Aluno) user;
        List<Professor> professores = alunoService.getProfessoresDisp(aluno.getMatricula());
        List<ProfessorDTO.RespostaApi> response = new ArrayList<>();
        for (Professor professor : professores) {
            response.add(new ProfessorDTO.RespostaApi(professor));
        }
        return ResponseEntity.ok(response);
    }
}