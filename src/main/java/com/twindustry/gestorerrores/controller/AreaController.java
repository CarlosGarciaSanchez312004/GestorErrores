package com.twindustry.gestorerrores.controller;

import com.twindustry.gestorerrores.model.Area;
import com.twindustry.gestorerrores.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/area")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @PostMapping("/{name}")
    public Area createArea(@PathVariable String name) {
        return areaService.createArea(name);
    }
    @GetMapping("/findAll")
    public List<Area> findAllArea(){
        return areaService.findAll();
    }
}
