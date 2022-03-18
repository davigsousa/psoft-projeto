package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.AdminUser;
import com.psoft.tccmatch.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT p FROM Professor p WHERE p.email = :email")
    Optional<Professor> findByEmail(@Param("email") String email);
}
