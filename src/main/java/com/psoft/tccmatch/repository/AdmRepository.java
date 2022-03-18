package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdmRepository extends JpaRepository<AdminUser, Long> {
    @Query("SELECT a FROM Admin_User a WHERE a.email = :email")
    Optional<AdminUser> findByEmail(@Param("email") String email);
}
