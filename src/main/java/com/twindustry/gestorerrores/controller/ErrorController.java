package com.twindustry.gestorerrores.controller;
import com.twindustry.gestorerrores.model.Error;
import com.twindustry.gestorerrores.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/errors")
@RequiredArgsConstructor
public class ErrorController {
    private final ErrorService errorService;

    @PostMapping("/create")
    public Error createError(@RequestBody Error error){
        return errorService.createError(
                error.getArea().getId(),
                error.getTopic().getId(),
                error.getDescriptionProblem(),
                error.getProyect().getId(),
                error.getReportedBy().getId()
        );
    }
    @PutMapping("/{id}/update")
    public Error updateError(@PathVariable Long id, @RequestBody Error error){
        return errorService.updateError(
                id,
                error.getReportedBy().getId(),
                error.getDescriptionProblem(),
                error.getArea().getId(),
                error.getTopic().getId()
        );
    }
    @PutMapping("/{id}/resolve")
    public Error resolveError(@PathVariable Long id, @RequestBody Error error){
        return errorService.resolveError(
                id,
                error.getSolution(),
                error.getDescriptionSolution(),
                error.getReportedBy().getId()
        );
    }
    @GetMapping("/all")
    public List<Error> findAllError(){
        return errorService.findAll();
    }
    @GetMapping("/{id}")
    public Error findByIdError(@PathVariable Long id){
        return errorService.findById(id);
    }
    @GetMapping("/filter")
    public List<Error> filterErrors(
            @RequestParam(required = false) Long areaId,
            @RequestParam(required = false) Long topicId,
            @RequestParam(required = false) Long reportedById,
            @RequestParam(required = false) Long proyectId){
        return errorService.filterErrors(areaId, proyectId, reportedById, topicId);
    }
}

