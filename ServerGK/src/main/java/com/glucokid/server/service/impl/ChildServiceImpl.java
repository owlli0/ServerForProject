package com.glucokid.server.service.impl;

import com.glucokid.server.domain.Child;
import com.glucokid.server.domain.ConnectionCode;
import com.glucokid.server.dto.ChildDTO;
import com.glucokid.server.repository.ChildRepository;
import com.glucokid.server.repository.ConnectionCodeRepository;
import com.glucokid.server.service.ChildService;
import com.glucokid.server.util.ChildMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;
    private final ConnectionCodeRepository connectionCodeRepository;

    @Override
    public ChildDTO add(ChildDTO dto) {
        Child child = new Child();
        child.setFirstName(dto.getFirstName());
        child.setSecondName(dto.getSecondName());
        child.setLastName(dto.getLastName());
        child.setPhoneNumber(dto.getPhoneNumber());
        child.setPassword(dto.getPassword());

        return ChildMapper.convertToDto(childRepository.save(child));
    }

    @Override
    public List<ChildDTO> getAll() {
        return childRepository.findAll().stream()
                .map(ChildMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChildDTO getById(Long id) {
        return childRepository.findById(id)
                .map(ChildMapper::convertToDto)
                .orElseThrow(() -> new RuntimeException("Child not found!"));
    }

    @Override
    public ChildDTO update(Long id, ChildDTO dto) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Child with ID " + id + " not found"));

        child.setFirstName(dto.getFirstName());
        child.setSecondName(dto.getSecondName());
        child.setLastName(dto.getLastName());
        child.setPhoneNumber(dto.getPhoneNumber());

        return ChildMapper.convertToDto(childRepository.save(child));
    }

    @Override
    public void deleteById(Long id) {
        childRepository.deleteById(id);
    }

    @Override
    @Transactional
    public String generateConnectionCode(Long childId) {
        // 1. Проверяем, существует ли ребенок
        if (!childRepository.existsById(childId)) {
            throw new RuntimeException("Ребенок не найден!");
        }
        // 2. Генерируем случайный 6-значный код (только цифры и заглавные буквы)
        String characters = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            codeBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        String generatedCode = codeBuilder.toString();

        ConnectionCode connectionCode = ConnectionCode.builder()
                .code(generatedCode)
                .childId(childId)
                .expiryDate(LocalDateTime.now().plusMinutes(10))
                .build();
        connectionCodeRepository.save(connectionCode);
        return generatedCode;
    }
}
