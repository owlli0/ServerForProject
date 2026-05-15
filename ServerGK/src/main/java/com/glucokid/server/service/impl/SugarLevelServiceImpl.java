package com.glucokid.server.service.impl;

import com.glucokid.server.domain.Child;
import com.glucokid.server.domain.SugarLevel;
import com.glucokid.server.dto.SugarLevelDTO;
import com.glucokid.server.repository.ChildRepository;
import com.glucokid.server.repository.SugarLevelRepository;
import com.glucokid.server.service.SugarLevelService;
import com.glucokid.server.util.SugarLevelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SugarLevelServiceImpl implements SugarLevelService {

    private final SugarLevelRepository sugarLevelRepository;

    private final ChildRepository childRepository;

    @Override
    public SugarLevelDTO add(SugarLevelDTO dto) {
        SugarLevel sugarLevel = SugarLevelMapper.convertToEntity(dto);

        if (dto.getChildId() != null) {
            Child child = childRepository.findById(dto.getChildId())
                    .orElseThrow(() -> new RuntimeException("Ребенок не найден!"));
            sugarLevel.setChild(child);
        }

        if (sugarLevel.getTime() == null) {
            sugarLevel.setTime(java.time.LocalDateTime.now());
        }

        return SugarLevelMapper.convertToDto(sugarLevelRepository.save(sugarLevel));
    }

    @Override
    public SugarLevelDTO getById(Long id) {
        return sugarLevelRepository.findById(id)
                .map(SugarLevelMapper::convertToDto)
                .orElseThrow(() -> new RuntimeException("Замер не найден!"));
    }

    @Override
    public List<SugarLevelDTO> getByChildId(Long childId) {
        return sugarLevelRepository.findAllByChildId(childId).stream()
                .map(SugarLevelMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SugarLevelDTO update(Long id, SugarLevelDTO dto) {
        SugarLevel sugarLevel = sugarLevelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Замер не найден!"));

        sugarLevel.setValue(dto.getValue());
        sugarLevel.setExtra(dto.getExtra());

        return SugarLevelMapper.convertToDto(sugarLevelRepository.save(sugarLevel));
    }

    @Override
    public void deleteById(Long id) {
        sugarLevelRepository.deleteById(id);
    }
}
