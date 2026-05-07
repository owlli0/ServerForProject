package com.glucokid.server.service;

import com.glucokid.server.dto.ChildDTO;

import java.util.List;

public interface ChildService {

    ChildDTO add(ChildDTO dto);
    List<ChildDTO> getAll();
    ChildDTO getById(Long id);
    ChildDTO update(Long id, ChildDTO dto);
    void deleteById(Long id);

    String generateConnectionCode(Long childId);

}
