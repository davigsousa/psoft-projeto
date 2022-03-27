package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.*;
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

    @Autowired
    private PropostaTCCService propostaTccService;

    @Override
    public Orientacao create(OrientacaoDTO dto) throws ApiException {
        Optional<Orientacao> orientacao_existe = orientacaoRepository.findById(dto.getIdPropostaTCC());

        if (orientacao_existe.isPresent()) {
            throw ErroOrientacao.erroOrientacaoJaExiste();
        }

        Professor professor = professorService.getById(dto.getIdProfessor());
        Aluno aluno = alunoService.getById(dto.getIdAluno());
        PropostaTCC propostaTcc = propostaTccService.getById(dto.getIdPropostaTCC());

        Orientacao orientacaoTCC = new Orientacao(propostaTcc, professor, aluno, dto.getPeriodoInicio());
        return orientacaoRepository.saveAndFlush(orientacaoTCC);
    }

    @Override
    public List<Orientacao> getAllActive() {
        return null;
    }

    @Override
    public List<Orientacao> getAllActive(Professor professor) {
        return orientacaoRepository.findAllByProfessorIdAndPeriodoFimNull(professor.getId());
    }

    @Override
    public List<Orientacao> getAllByProfessor(Professor professor) {
        return orientacaoRepository.findAllByProfessorId(professor.getId());
    }

    @Override
    public OrientacaoDTO.RespostaApiLista getAllByPeriodo(String periodo) {
        List<Orientacao> emCurso = getAllEmCurso();
        List<Orientacao> finalizadas = getAllByPeriodoFinalizadas(periodo);

        return new OrientacaoDTO.RespostaApiLista(emCurso, finalizadas);
    }

    @Override
    public Orientacao getById(Long id) throws ApiException {
        Optional<Orientacao> orientacao = orientacaoRepository.findById(id);

        if (orientacao.isEmpty()) {
            throw ErroOrientacao.erroOrientacaoNaoExiste();
        }

        return orientacao.get();
    }

    @Override
    public List<Orientacao> getAllEmCurso() {
        return orientacaoRepository.findAllByPeriodoFimIsNull();
    }

    @Override
    public List<Orientacao> getAllByPeriodoFinalizadas(String periodo) {
        return orientacaoRepository.findAllByPeriodoFim(periodo);
    }

    @Override
    public void finalizarOrientacao(Long idOrientacao, String periodoFim) throws ApiException {
        Orientacao orientacao = getById(idOrientacao);

        orientacao.setPeriodoFim(periodoFim);
        orientacaoRepository.saveAndFlush(orientacao);
    }

    @Override
    public OrientacaoDTO.RespostaApiLista buscaPorPeriodo(String periodo) {
        List<Orientacao> orientacoes_finalizada = this.buscaPorPeriodoFinalizada(periodo);
        List<Orientacao> orientacoes_cursando = this.buscaPorPeriodoEmCurso(periodo);

        return new OrientacaoDTO.RespostaApiLista(orientacoes_cursando, orientacoes_finalizada);
    }

    private List<Orientacao> buscaPorPeriodoFinalizada(String periodo) {
        return orientacaoRepository.findAllByPeriodoInicioAndPeriodoFimIsNotNull(periodo);
    }

    private List<Orientacao> buscaPorPeriodoEmCurso(String periodo) {
        return orientacaoRepository.findAllByPeriodoInicioAndPeriodoFimIsNull(periodo);
    }
}
