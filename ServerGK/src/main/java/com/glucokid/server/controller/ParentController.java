package com.glucokid.server.controller;

import com.glucokid.server.domain.Parent;
import com.glucokid.server.dto.ChildDTO;
import com.glucokid.server.dto.ParentDTO;
import com.glucokid.server.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parent")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public List<ParentDTO> getAll() {
        return parentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<ParentDTO> add(@RequestBody ParentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parentService.add(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentDTO> update(@PathVariable long id, @RequestBody ParentDTO dto) {
        return ResponseEntity.ok(parentService.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        parentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{parentId}/connect")
    public ResponseEntity<String> connectChild(@PathVariable Long parentId, @RequestParam String code) {
        parentService.connectChild(parentId, code);
        return ResponseEntity.ok("Ребенок успешно подключен!");
    }

    @GetMapping("/{parentId}/children")
    public ResponseEntity<List<ChildDTO>> getMyChildren(@PathVariable Long parentId) {
        return ResponseEntity.ok(parentService.getMyChildren(parentId));
    }

    @PostMapping("/login")public ResponseEntity<ParentDTO> login(@RequestBody ParentDTO parentDTO) {
        return ResponseEntity.ok(parentService.login(parentDTO.getPhoneNumber(), parentDTO.getPassword()));
    }
}
