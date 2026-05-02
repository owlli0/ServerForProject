package com.glucokid.server.repository;

import com.glucokid.server.domain.ParentChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentChildRepository extends JpaRepository<ParentChild, Long> {
}
