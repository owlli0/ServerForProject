package com.glucokid.server.service;

import com.glucokid.server.domain.ConnectionCode;

public interface ConnectionCodeService {
    String createCode(Long childId);
    ConnectionCode validateAndGet(String code);
    void remove(ConnectionCode connectionCode);
}
