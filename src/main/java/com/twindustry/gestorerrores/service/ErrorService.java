package com.twindustry.gestorerrores.service;

import com.twindustry.gestorerrores.model.*;
import com.twindustry.gestorerrores.model.Error;
import com.twindustry.gestorerrores.repository.*;
import com.twindustry.gestorerrores.specification.ErrorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorService {
    private final UserRepository userRepository;
    private final ProyectRepository proyectRepository;
    private final ErrorRepository errorRepository;
    private final AreaRepository areaRepository;
    private final TopicRepository topicRepository;

    @Transactional
    public Error createError(Long areaId, Long topicId, String descriptionProblem, Long reportedById, Long proyectId) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new IllegalArgumentException("Area inexistente"));
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new IllegalArgumentException("Topic inexistente"));
        Proyect proyect = proyectRepository.findById(proyectId).orElseThrow(() -> new IllegalArgumentException("Proyect inexistente"));
        User user = userRepository.findById(reportedById).orElseThrow(() -> new IllegalArgumentException("Usuario inexistente"));

    Error error = new Error();
    error.setDescriptionProblem(descriptionProblem);
    error.setArea(area);
    error.setTopic(topic);
    error.setProyect(proyect);
    error.setReportedBy(user);
    error.setStatus(Status.PENDIENTE);
    error.setCreatedAt(LocalDateTime.now());
    return errorRepository.save(error);
    }
    @Transactional
    public Error updateError(Long errorId, Long requestingUserId, String descriptionProblem, Long areaId, Long topicId) {
        Error error = errorRepository.findById(errorId)
                .orElseThrow(() -> new IllegalArgumentException("Error inexistente"));
        User user = userRepository.findById(requestingUserId).orElseThrow(() -> new IllegalArgumentException("Usuario inexistente"));
        Area area= areaRepository.findById(areaId).orElseThrow(() -> new IllegalArgumentException("Area inexistente"));
        Topic  topic = topicRepository.findById(topicId).orElseThrow(() -> new IllegalArgumentException("Topic_inexistente"));
        if (!error.getReportedBy().getId().equals(requestingUserId) && user.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("No tienes permisos para modificar este error");
        }
            error.setDescriptionProblem(descriptionProblem);
            error.setArea(area);
            error.setTopic(topic);
            error.setUpdatedAt(LocalDateTime.now());
            return errorRepository.save(error);
        }

    @Transactional
    public Error resolveError(Long errorId, String solution, String descriptionSolution, Long requestingUserId) {
    Error error = errorRepository.findById(errorId)
                .orElseThrow(() -> new IllegalArgumentException("Error inexistente"));
    User user = userRepository.findById(requestingUserId).orElseThrow(() -> new IllegalArgumentException("Usuario inexistente"));
        if (!error.getReportedBy().getId().equals(requestingUserId) && user.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("No tienes permisos para modificar este error");
        }
        error.setStatus(Status.RESUELTO);
        error.setSolution(solution);
        error.setDescriptionSolution(descriptionSolution);
        error.setUpdatedAt(LocalDateTime.now());
        return errorRepository.save(error);
    }
    @Transactional(readOnly=true)
    public List<Error> findAll() {
        return errorRepository.findAll();
    }
    @Transactional(readOnly=true)
    public Error findById(Long id) {
        return errorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error inexistente"));
    }

    @Transactional(readOnly = true)
    public List<Error> filterErrors(Long areaId, Long proyectId, Long reportedById, Long topicId) {
        Proyect proyect = proyectId != null
                ? proyectRepository.findById(proyectId).orElse(null) : null;

        Area area = areaId != null
                ? areaRepository.findById(areaId).orElse(null) : null;

        Topic topic = topicId != null
                ? topicRepository.findById(topicId).orElse(null) : null;

        User user = reportedById != null
                ? userRepository.findById(reportedById).orElse(null) : null;

        Specification<Error> spec = Specification
                .where(ErrorSpecification.hasArea(area))
                .and(ErrorSpecification.hasTopic(topic))
                .and(ErrorSpecification.hasReportedBy(user))
                .and(ErrorSpecification.hasProyect(proyect));

        return errorRepository.findAll(spec);
    }
}

