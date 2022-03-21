package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.TCC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TCCRepository extends JpaRepository<TCC, Long> {
    Optional<TCC> findByTitulo(String titulo);

}
