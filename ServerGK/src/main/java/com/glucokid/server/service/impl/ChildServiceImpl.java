package com.glucokid.server.service.impl;

import com.glucokid.server.domain.Child;
import com.glucokid.server.domain.ConnectionCode;
import com.glucokid.server.dto.ChildDTO;
import com.glucokid.server.repository.ChildRepository;
import com.glucokid.server.repository.ConnectionCodeRepository;
import com.glucokid.server.service.ChildService;
import com.glucokid.server.service.ConnectionCodeService;
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
    private final ConnectionCodeService connectionCodeService;;

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

        if (dto.getFirstName() != null) {
            child.setFirstName(dto.getFirstName());
        }
        if (dto.getSecondName() != null) {
            child.setSecondName(dto.getSecondName());
        }
        if (dto.getLastName() != null) {
            child.setLastName(dto.getLastName());
        }
        if (dto.getPhoneNumber() != null) {
            child.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getPassword() != null) {
            child.setPassword(dto.getPassword());
        }
        return ChildMapper.convertToDto(childRepository.save(child));
    }

    @Override
    public void deleteById(Long id) {
        childRepository.deleteById(id);
    }

    @Override
    @Transactional
    public String generateConnectionCode(Long childId) {
        if (!childRepository.existsById(childId)) {
            throw new RuntimeException("Ребенок не найден!");
        }
        return connectionCodeService.createCode(childId);
    }

    @Override
    public ChildDTO login(String phoneNumber, String password) {
        return childRepository.findByPhoneNumberAndPassword(phoneNumber, password)
                .map(ChildMapper::convertToDto)
                .orElseThrow(() -> new RuntimeException("Неверный телефон или пароль ребенка"));
    }
}
