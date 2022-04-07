package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.AreaEstudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AreaEstudoRepository extends JpaRepository<AreaEstudo, Long> {
    Optional<AreaEstudo> findByAssunto(String assunto);
}
