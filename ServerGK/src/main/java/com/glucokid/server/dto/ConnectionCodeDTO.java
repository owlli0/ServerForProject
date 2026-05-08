package com.glucokid.server.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConnectionCodeDTO {
    private String code;
    private Long childId;
    private LocalDateTime expiryDate;
}
