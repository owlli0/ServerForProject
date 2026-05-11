package com.glucokid.server.controller;

import com.glucokid.server.dto.SugarLevelDTO;
import com.glucokid.server.service.SugarLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sugar")
@RequiredArgsConstructor
public class SugarLevelController {

    private final SugarLevelService sugarLevelService;

    @PostMapping("/add")
    public ResponseEntity<SugarLevelDTO> add(@RequestBody SugarLevelDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sugarLevelService.add(dto));
    }

    @GetMapping("/child/{childId}")
    public ResponseEntity<List<SugarLevelDTO>> getByChildId(@PathVariable Long childId) {
        return ResponseEntity.ok(sugarLevelService.getByChildId(childId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SugarLevelDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sugarLevelService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SugarLevelDTO> update(@PathVariable Long id, @RequestBody SugarLevelDTO dto) {
        return ResponseEntity.ok(sugarLevelService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sugarLevelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
