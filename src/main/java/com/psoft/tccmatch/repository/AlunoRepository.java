package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.AdminUser;
import com.psoft.tccmatch.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT a FROM Aluno a WHERE a.matricula = :matricula")
    Optional<Aluno> findByMatricula(@Param("matricula") String matricula);

    Optional<Aluno> findByEmail(String email);
}
