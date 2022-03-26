package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByEmail(String email);

    List<Professor> findAllByDisponivelTrueAndAreasEstudoIn(List<AreaEstudo> areas);
}