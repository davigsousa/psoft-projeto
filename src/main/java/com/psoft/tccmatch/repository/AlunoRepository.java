package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT a FROM Aluno a WHERE a.matricula = :matricula")
    Optional<Aluno> findByMatricula(@Param("matricula") String matricula);

    Optional<Aluno> findByEmail(String email);


    List<Aluno> findAllByAreasEstudoIn(Set<AreaEstudo> areaEstudos);
}
