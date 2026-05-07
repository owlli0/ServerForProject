package com.glucokid.server.dto;

import lombok.Data;

@Data
public class ParentChildDTO {
    private Long id;
    private Long parentId;
    private Long childId;
}
