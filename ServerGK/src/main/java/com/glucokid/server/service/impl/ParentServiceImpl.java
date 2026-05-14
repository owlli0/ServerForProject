package com.glucokid.server.service.impl;

import com.glucokid.server.domain.Child;
import com.glucokid.server.domain.ConnectionCode;
import com.glucokid.server.domain.Parent;
import com.glucokid.server.domain.ParentChild;
import com.glucokid.server.dto.ChildDTO;
import com.glucokid.server.dto.ParentDTO;
import com.glucokid.server.repository.ChildRepository;
import com.glucokid.server.repository.ConnectionCodeRepository;
import com.glucokid.server.repository.ParentChildRepository;
import com.glucokid.server.repository.ParentRepository;
import com.glucokid.server.service.ConnectionCodeService;
import com.glucokid.server.service.ParentService;
import com.glucokid.server.util.ChildMapper;
import com.glucokid.server.util.ParentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final ConnectionCodeService connectionCodeService;
    private final ConnectionCodeRepository connectionCodeRepository;
    private final ParentChildRepository parentChildRepository;

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

    @Override
    @Transactional
    public ChildDTO connectChild(Long parentId, String code) {
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Родитель не найден"));

        ConnectionCode connectionCode = connectionCodeRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Неверный код подключения"));

        if (connectionCode.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Срок действия кода истек");
        }

        Child child = childRepository.findById(connectionCode.getChildId())
                .orElseThrow(() -> new RuntimeException("Ребенок не найден в базе"));

        ParentChild relation = ParentChild.builder()
                .parent(parent)
                .child(child)
                .build();
        parentChildRepository.save(relation);

        connectionCodeRepository.delete(connectionCode);

        return ChildMapper.convertToDto(child);
    }

    @Override
    public List<ChildDTO> getMyChildren(Long parentId) {
        List<ChildDTO> childrenDtos = new ArrayList<>();

        List<ParentChild> relations = parentChildRepository.findAllByParentId(parentId);

        for (ParentChild relation : relations) {
            Child child = relation.getChild();
            ChildDTO dto = ChildMapper.convertToDto(child);
            childrenDtos.add(dto);
        }

        return childrenDtos;
    }

    @Override
    public ParentDTO login(String phoneNumber, String password) {
        return parentRepository.findByPhoneNumberAndPassword(phoneNumber, password)
                .map(ParentMapper::convertToDto)
                .orElseThrow(() -> new RuntimeException("Неверный телефон или пароль родителя"));
    }
}
