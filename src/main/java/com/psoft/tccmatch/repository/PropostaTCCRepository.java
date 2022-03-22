package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.PropostaTCC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropostaTCCRepository extends JpaRepository<PropostaTCC, Long> {
    Optional<PropostaTCC> findByTitulo(String titulo);

    @Query("SELECT p FROM PropostaTCC p JOIN p.professor WHERE p.professor IS NOT NULL")
    List<PropostaTCC> findCriadoByProfessor();
}
