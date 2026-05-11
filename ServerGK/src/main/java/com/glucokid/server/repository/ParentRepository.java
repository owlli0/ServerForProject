package com.glucokid.server.repository;

import com.glucokid.server.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByPhoneNumberAndPassword(String phoneNumber, String password);
}
