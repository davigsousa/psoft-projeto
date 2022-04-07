package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.*;
import com.psoft.tccmatch.processors.ReporteProcessor.ReporteProcessor;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.repository.OrientacaoRepository;
import com.psoft.tccmatch.repository.ProfessorRepository;
import com.psoft.tccmatch.repository.ReporteRepository;
import com.psoft.tccmatch.util.ErroReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private OrientacaoRepository orientacaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    private final Map<String, ReporteProcessor> reporteProcessors;

    public ReporteServiceImpl(Map<String, ReporteProcessor> reporteProcessors) {
        this.reporteProcessors = reporteProcessors;
    }

    @Override
    public Reporte cria(User user, Long orientacao_id, ReporteDTO dto) throws ApiException {
        Optional<Orientacao> orientacao_opt = orientacaoRepository.findById(orientacao_id);

        if (orientacao_opt.isEmpty()) {
            throw ErroReporte.erroReporteJaExiste();
        }

        Orientacao orientacao = orientacao_opt.get();
        Reporte reporte = new Reporte(dto.getPeriodo(), dto.getProblema(), orientacao);

        return reporteProcessors.get(getProcessorName(user)).criar(
                user, reporte, orientacao, dto
        );
    }

    @Override
    public List<Reporte> buscaPorPeriodoAluno(String periodo) {
        return reporteRepository.findAllByPeriodoAndAlunoIsNotNull(periodo);
    }

    @Override
    public List<Reporte> buscaPorPeriodoProfessor(String periodo) {
        return reporteRepository.findAllByPeriodoAndProfessorIsNotNull(periodo);
    }

    @Override
    public ReporteDTO.RespostaApiLista buscaPorPeriodo(String periodo) {
        List<Reporte> reportes_aluno = this.buscaPorPeriodoAluno(periodo);
        List<Reporte> reportes_professores = this.buscaPorPeriodoProfessor(periodo);

        return new ReporteDTO.RespostaApiLista(reportes_aluno, reportes_professores);
    }

    private String getProcessorName(User user) {
        return user.getTipo().name() + "_Reporte";
    }
}
