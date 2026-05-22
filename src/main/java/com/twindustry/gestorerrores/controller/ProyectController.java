package com.twindustry.gestorerrores.controller;

import com.twindustry.gestorerrores.model.Proyect;
import com.twindustry.gestorerrores.service.ProyectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyects")
@RequiredArgsConstructor
public class ProyectController {
    private final ProyectService proyectService;

    @PostMapping("/create")
    public Proyect createProyect(@RequestBody Proyect proyect){
        return proyectService.createProyect(
                proyect.getName(),
                proyect.getDescription(),
                proyect.getCreatedBy().getId()
        );
    }

    @PutMapping("/{proyectId}/assign/{userId}")
    public Proyect assignUserToProyect(@PathVariable Long proyectId, @PathVariable Long userId){
        return proyectService.assignUserToProyect(proyectId, userId);
    }
    @GetMapping("/findAll")
    public List<Proyect> findAll(){
        return proyectService.findAll();
    }
    @GetMapping("/find/{id}")
    public Proyect getProyect(@PathVariable Long id){
        return proyectService.findById(id);
    }
    @GetMapping("/{userId}/proyects")
    public List<Proyect> getProyects(@PathVariable Long userId){
        return proyectService.findByUser(userId);
    }




}
