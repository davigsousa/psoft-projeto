package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.Orientacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrientacaoRepository extends JpaRepository<Orientacao, Long> {
    List<Orientacao> findAllByProfessorIdAndPeriodoFimNull(Long id);
}
