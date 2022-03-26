package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    Optional<Reporte> findByAlunoAndPeriodoAndProblemaAndOrientacao(
            Aluno aluno,
            String periodo,
            String problema,
            Orientacao orientacao
    );

    Optional<Reporte> findByProfessorAndPeriodoAndProblemaAndOrientacao(
            Professor professor,
            String periodo,
            String problema,
            Orientacao orientacao
    );

    List<Reporte> findAllByPeriodoAndProfessorIsNotNull(String periodo);

    List<Reporte> findAllByPeriodoAndAlunoIsNotNull(String periodo);

}
