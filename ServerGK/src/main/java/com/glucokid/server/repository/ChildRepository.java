package com.glucokid.server.repository;

import com.glucokid.server.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findByPhoneNumberAndPassword(String phoneNumber, String password);
}
