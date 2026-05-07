package com.glucokid.server.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SugarLevelDTO {

    private Long id;
    private Long childId;
    private Double value;
    private LocalDateTime time;
    private String extra;
}
