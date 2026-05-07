package com.glucokid.server.repository;

import com.glucokid.server.domain.ConnectionCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConnectionCodeRepository extends JpaRepository<ConnectionCode, Long> {
    Optional<ConnectionCode> findByCode(String code);
}
