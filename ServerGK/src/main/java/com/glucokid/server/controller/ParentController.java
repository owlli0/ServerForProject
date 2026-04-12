package com.glucokid.server.controller;

import com.glucokid.server.domain.Parent;
import com.glucokid.server.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @PostMapping("/parent")
    public Parent add(@RequestBody Parent parent) {
        return parentService.add(parent);
    }

    @GetMapping("/parent")
    public List<Parent> getAll() {
        return parentService.getAll();
    }

    @PostMapping("/parent/{id}")
    public Parent getById(@PathVariable Long id) {
        return parentService.getById(id);
    }

    @PutMapping("/parent/{id}")
    public Parent update(@PathVariable long id, @RequestBody Parent parent) {
        return parentService.update(id, parent);
    }

    @DeleteMapping("/parent/{id}")
    public void deleteById(@PathVariable long id) {
        parentService.deleteById(id);
    }
}
