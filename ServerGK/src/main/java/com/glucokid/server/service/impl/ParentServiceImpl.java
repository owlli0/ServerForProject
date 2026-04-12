package com.glucokid.server.service.impl;

import com.glucokid.server.dao.ParentRepository;
import com.glucokid.server.domain.Parent;
import com.glucokid.server.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Override
    public Parent add(Parent parent) {

        return parentRepository.save(parent);
    }

    @Override
    public List<Parent> getAll() {
        return parentRepository.findAll();
    }

    @Override
    public Parent getById(Long id) {
        Optional<Parent> parentOptional = parentRepository.findById(id);
        if (!parentOptional.isPresent()) throw  new RuntimeException("Parent with ID " + id + " not found");

        return parentOptional.get();
    }

    @Override
    public Parent update(Long id, Parent parent) {
        Optional<Parent> parentOptional = parentRepository.findById(id);
        if (!parentOptional.isPresent()) throw  new RuntimeException("Parent with ID " + id + " not found");

        Parent updateParent = parentOptional.get();
        updateParent.setFirstName(parent.getFirstName());
        updateParent.setSecondName(parent.getSecondName());
        updateParent.setLastName(parent.getLastName());
        updateParent.setPhoneNumber(parent.getPhoneNumber());

        return parentRepository.save(updateParent);
    }

    @Override
    public void deleteById(Long id) {
        parentRepository.deleteById(id);
    }
}
