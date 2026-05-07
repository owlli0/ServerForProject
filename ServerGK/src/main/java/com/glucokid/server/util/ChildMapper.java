package com.glucokid.server.util;

import com.glucokid.server.domain.Child;
import com.glucokid.server.dto.ChildDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChildMapper {
    public static ChildDTO convertToDto(Child child) {
        ChildDTO dto = new ChildDTO();

        dto.setId(child.getId());
        dto.setFirstName(child.getFirstName());
        dto.setSecondName(child.getSecondName());
        dto.setLastName(child.getLastName());
        dto.setPhoneNumber(child.getPhoneNumber());
        dto.setPassword(child.getPassword());

        return dto;
    }
}
