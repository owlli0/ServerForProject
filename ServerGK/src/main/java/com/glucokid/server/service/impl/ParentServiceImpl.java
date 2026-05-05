package com.glucokid.server.service.impl;

import com.glucokid.server.domain.Parent;
import com.glucokid.server.dto.ParentDTO;
import com.glucokid.server.repository.ParentRepository;
import com.glucokid.server.service.ParentService;
import com.glucokid.server.util.ParentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Override
    public List<ParentDTO> getAll() {
        return parentRepository.findAll().stream()
                .map(ParentMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParentDTO getById(Long id) {
        return parentRepository.findById(id).map(ParentMapper::convertToDto)
                .orElseThrow(() -> new RuntimeException("Parent not found!"));
    }

    @Override
    public ParentDTO add(ParentDTO dto) {

        Parent parent = new Parent();
        parent.setFirstName(dto.getFirstName());
        parent.setSecondName(dto.getSecondName());
        parent.setLastName(dto.getLastName());
        parent.setPhoneNumber(dto.getPhoneNumber());
        parent.setPassword(dto.getPassword());

        return ParentMapper.convertToDto(parentRepository.save(parent));
    }

    @Override
    public ParentDTO update(Long id, ParentDTO dto) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() ->new RuntimeException("Parent with ID " + id + " not found"));
        
        parent.setFirstName(dto.getFirstName());
        parent.setSecondName(dto.getSecondName());
        parent.setLastName(dto.getLastName());
        parent.setPhoneNumber(dto.getPhoneNumber());

        return ParentMapper.convertToDto(parentRepository.save(parent));
    }

    @Override
    public void deleteById(Long id) {
        parentRepository.deleteById(id);
    }
}
