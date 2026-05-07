package com.glucokid.server.util;

import com.glucokid.server.domain.SugarLevel;
import com.glucokid.server.dto.SugarLevelDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SugarLevelMapper {
    public SugarLevelDTO convertToDto(SugarLevel entity) {
        SugarLevelDTO dto = new SugarLevelDTO();

        dto.setId(entity.getId());
        dto.setValue(entity.getValue());
        dto.setTime(entity.getTime());
        dto.setExtra(entity.getExtra());

        if (entity.getChild() != null) {
            dto.setChildId(entity.getChild().getId());
        }
        return dto;
    }

    public SugarLevel convertToEntity(SugarLevelDTO dto) {
        SugarLevel entity = new SugarLevel();

        entity.setId(dto.getId());
        entity.setValue(dto.getValue());
        entity.setTime(dto.getTime());
        entity.setExtra(dto.getExtra());

        return entity;
    }
}
