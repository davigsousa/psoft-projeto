package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.Orientacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrientacaoRepository extends JpaRepository<Orientacao, Long> {
    @Query("SELECT a FROM Orientacao a WHERE a.theme = :theme")
    Optional<Orientacao> findByTheme(@Param("theme") String theme);
}
