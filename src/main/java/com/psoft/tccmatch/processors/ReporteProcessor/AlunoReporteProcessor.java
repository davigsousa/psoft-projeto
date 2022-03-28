package com.psoft.tccmatch.processors.ReporteProcessor;

import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.*;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.repository.ReporteRepository;
import com.psoft.tccmatch.util.ErroReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("ALUNO_Reporte")
public class AlunoReporteProcessor implements ReporteProcessor {
    @Lazy
    @Autowired
    private AlunoRepository alunoRepository;

    @Lazy
    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public Reporte criar(User user, Reporte reporte, Orientacao orientacao, ReporteDTO dto) throws ApiException {
        Aluno aluno = alunoRepository.getOne(((Aluno) user).getId());

        Optional<Reporte> reporte_opt = reporteRepository.findByAlunoAndPeriodoAndProblemaAndOrientacao(
                aluno,
                dto.getPeriodo(),
                dto.getProblema(),
                orientacao
        );

        if (reporte_opt.isPresent()) {
            throw ErroReporte.erroReporteJaExiste();
        }
        reporte.setAluno(aluno);

        return reporteRepository.save(reporte);
    }
}
