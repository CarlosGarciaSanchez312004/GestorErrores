package com.twindustry.gestorerrores.service;


import com.twindustry.gestorerrores.model.Area;
import com.twindustry.gestorerrores.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final AreaRepository areaRepository;

    @Transactional
    public Area createArea(String name){
        Area area = new Area();
        area.setName(name);
        return areaRepository.save(area);
    }
    @Transactional (readOnly = true)
    public List<Area> findAll(){
        return areaRepository.findAll();
    }
}
