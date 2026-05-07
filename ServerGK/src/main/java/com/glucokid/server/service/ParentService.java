package com.glucokid.server.service;

import com.glucokid.server.domain.Parent;
import com.glucokid.server.dto.ChildDTO;
import com.glucokid.server.dto.ParentDTO;

import java.util.List;

public interface ParentService {

    ParentDTO add(ParentDTO dto);
    List<ParentDTO> getAll();
    ParentDTO getById(Long id);
    ParentDTO update(Long id, ParentDTO dto);
    void deleteById(Long id);
    void connectChild(Long parentId, String code);
    List<ChildDTO> getMyChildren(Long parentId);
}
