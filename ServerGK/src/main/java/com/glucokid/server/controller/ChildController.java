package com.glucokid.server.controller;


import com.glucokid.server.dto.ChildDTO;
import com.glucokid.server.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/child")
@RequiredArgsConstructor
public class ChildController {
    private final ChildService childService;

    @GetMapping
    public List<ChildDTO> getAll() {
        return childService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChildDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(childService.getById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<ChildDTO> add(@RequestBody ChildDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(childService.add(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChildDTO> update(@PathVariable Long id, @RequestBody ChildDTO dto) {
        return ResponseEntity.ok(childService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        childService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{childId}/generate-code")
    public ResponseEntity<String> generateConnectionCode(@PathVariable Long childId) {
        String code = childService.generateConnectionCode(childId);
        return ResponseEntity.ok(code);
    }
}
