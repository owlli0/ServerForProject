package com.glucokid.server.repository;

import com.glucokid.server.domain.ParentChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParentChildRepository extends JpaRepository<ParentChild, Long> {
    List<ParentChild> findAllByParentId(Long parentId);
    Optional<ParentChild> findByParentIdAndChildId(Long parentId, Long childId);
}
