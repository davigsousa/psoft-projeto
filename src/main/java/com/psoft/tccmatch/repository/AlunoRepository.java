package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AlunoRepository extends Repository<Aluno, Long> {
    Optional<Aluno> findByMatricula(String matricula);
}
