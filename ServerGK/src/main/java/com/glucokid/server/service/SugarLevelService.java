package com.glucokid.server.service;

import com.glucokid.server.dto.SugarLevelDTO;

import java.util.List;

public interface SugarLevelService {

    SugarLevelDTO add(SugarLevelDTO dto);
    SugarLevelDTO getById(Long id);
    List<SugarLevelDTO> getByChildId(Long childId);
    SugarLevelDTO update(Long id, SugarLevelDTO dto);
    void deleteById(Long id);
}
