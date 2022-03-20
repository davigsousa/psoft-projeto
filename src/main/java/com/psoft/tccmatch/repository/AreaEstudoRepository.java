package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.AreaEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AreaEstudoRepository extends JpaRepository<AreaEstudo, Long> {
    @Query("SELECT a FROM AreaEstudo a where LOWER(a.label) = LOWER(:label)")
    Optional<AreaEstudo> findByLabel(@Param("label") String label);
}
