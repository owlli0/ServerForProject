package com.glucokid.server.repository;

import com.glucokid.server.domain.SugarLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SugarLevelRepository extends JpaRepository<SugarLevel, Long> {
    List<SugarLevel> findAllByChildId(Long childId);
}
