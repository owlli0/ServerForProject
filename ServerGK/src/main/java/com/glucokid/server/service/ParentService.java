package com.glucokid.server.service;

import com.glucokid.server.domain.Parent;

import java.util.List;

public interface ParentService {

    Parent add(Parent parent);
    List<Parent> getAll();
    Parent getById(Long id);
    Parent update(Long id, Parent parent);
    void deleteById(Long id);
}
