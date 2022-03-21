package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.repository.OrientacaoRepository;
import com.psoft.tccmatch.util.ErroOrientacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrientacaoServiceImpl implements OrientacaoService {
    @Autowired
    private OrientacaoRepository orientacaoRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AreaEstudoService areaEstudoService;

    @Override
    public Orientacao create(OrientacaoDTO dto) throws ApiException {
        Optional<Orientacao> orientacao_existe = orientacaoRepository.findByTheme(dto.getTheme());

        if (orientacao_existe.isPresent()) {
            throw ErroOrientacao.erroOrientacaoJaExiste();
        }

        Professor professor = professorService.getById(dto.getIdProfessor());
        Aluno aluno = alunoService.getById(dto.getIdAluno());
        AreaEstudo areaEstudo = areaEstudoService.getById(dto.getIdAreaInteresse());

        Orientacao orientacaoTCC = new Orientacao(dto.getTheme(), professor, aluno, areaEstudo, dto.getPeriodoTCC(), dto.getStatusAprovacao());
        return orientacaoRepository.save(orientacaoTCC);
    }

    @Override
    public Orientacao update(OrientacaoDTO dto) throws ApiException {
        return null;
    }

    @Override
    public Orientacao get(String tema) throws ApiException {
        //apenas com orientação True
        return null;
    }

    @Override
    public List<Orientacao> getAll() {
        return null;
    }

    @Override
    public void remove(String tema) throws ApiException {

    }
}
