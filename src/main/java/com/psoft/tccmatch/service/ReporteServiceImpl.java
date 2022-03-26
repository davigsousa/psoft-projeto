package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.model.Reporte;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.repository.OrientacaoRepository;
import com.psoft.tccmatch.repository.ProfessorRepository;
import com.psoft.tccmatch.repository.ReporteRepository;
import com.psoft.tccmatch.util.ErroReporte;
import com.psoft.tccmatch.util.ErroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public Reporte cria(Object user, Long orientacao_id, ReporteDTO dto) throws ApiException {
        Optional<Orientacao> orientacao_opt = orientacaoRepository.findById(orientacao_id);

        if (orientacao_opt.isEmpty()) {
            throw ErroReporte.erroReporteJaExiste();
        }

        Orientacao orientacao = orientacao_opt.get();
        Reporte reporte = new Reporte(dto.getPeriodo(), dto.getProblema(), orientacao);

        if (user instanceof Aluno) {
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
            reporte.setAluno((Aluno) user);
        } else if (user instanceof Professor) {
            Professor professor = professorRepository.getOne(((Professor) user).getId());
            Optional<Reporte> reporte_opt = reporteRepository.findByProfessorAndPeriodoAndProblemaAndOrientacao(
                    professor,
                    dto.getPeriodo(),
                    dto.getProblema(),
                    orientacao
            );
            if (reporte_opt.isPresent()) {
                throw ErroReporte.erroReporteJaExiste();
            }
            reporte.setProfessor((Professor) user);
        } else {
            throw ErroUser.erroTipoUsuario();
        }

        return reporteRepository.save(reporte);
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


}
