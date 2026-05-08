package com.glucokid.server.service.impl;

import com.glucokid.server.domain.ConnectionCode;
import com.glucokid.server.repository.ConnectionCodeRepository;
import com.glucokid.server.service.ConnectionCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConnectionCodeServiceImpl implements ConnectionCodeService {


    private final ConnectionCodeRepository repository;
    private final SecureRandom random = new SecureRandom();
    private static final String CHARS = "0123456789";

    @Override
    public String createCode(Long childId) {
        repository.deleteByChildId(childId);

        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        String codeText = sb.toString();
        ConnectionCode codeEntity = ConnectionCode.builder()
                .code(codeText)
                .childId(childId)
                .expiryDate(LocalDateTime.now().plusMinutes(10))
                .build();

        repository.save(codeEntity);
        return codeText;
    }

    @Override
    public ConnectionCode validateAndGet(String code) {
        ConnectionCode connectionCode = repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Код не найден!"));

        if (connectionCode.getExpiryDate().isBefore(LocalDateTime.now())) {
            repository.delete(connectionCode);
            throw new RuntimeException("Срок действия кода истек!");
        }

        return connectionCode;
    }

    @Override
    public void remove(ConnectionCode connectionCode) {
        repository.delete(connectionCode);
    }
}
