package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.PropostaTCC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TCCRepository extends JpaRepository<PropostaTCC, Long> {
    Optional<PropostaTCC> findByTitulo(String titulo);

}
