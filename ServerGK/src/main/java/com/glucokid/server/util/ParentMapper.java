package com.glucokid.server.util;

import com.glucokid.server.domain.Parent;
import com.glucokid.server.dto.ParentDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParentMapper {
    public ParentDTO convertToDto(Parent parent) {
        ParentDTO dto = new ParentDTO();
        dto.setId(dto.getId());
        dto.setFirstName(parent.getFirstName());
        dto.setSecondName(parent.getSecondName());
        dto.setLastName(parent.getLastName());
        dto.setPhoneNumber(parent.getPhoneNumber());
        dto.setPassword(parent.getPassword());
        return dto;
    }
}
