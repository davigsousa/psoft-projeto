package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminUser, Long> {
}
