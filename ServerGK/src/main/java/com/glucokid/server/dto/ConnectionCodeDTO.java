package com.glucokid.server.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConnectionCodeDTO {
    private String code;       // Сам код (например, "AX392Z")
    private Long childId;      // ID ребенка, который его создал
    private LocalDateTime expiryDate;
}
