package com.twindustry.gestorerrores.service;

import com.twindustry.gestorerrores.model.Proyect;
import com.twindustry.gestorerrores.model.User;
import com.twindustry.gestorerrores.repository.ProyectRepository;
import com.twindustry.gestorerrores.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectService {
private final ProyectRepository proyectRepository;
private final UserRepository userRepository;
@Transactional
public Proyect createProyect(String name, String description, Long createdById) {
    User createdBy  = userRepository.findById(createdById).
            orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
Proyect proyect = new Proyect();
proyect.setName(name);
proyect.setDescription(description);
proyect.setCreatedBy(createdBy);
proyect.setCreatedAt(LocalDateTime.now());
return proyectRepository.save(proyect);
    }
@Transactional
public Proyect assignUserToProyect(Long proyectId, Long userId) {
    Proyect proyect = proyectRepository.findById(proyectId)
            .orElseThrow(()-> new IllegalArgumentException("Proyect no encontrado"));
    User user = userRepository.findById(userId)
            .orElseThrow(()-> new IllegalArgumentException("Usuario no encontrado"));

    proyect.getUsers().add(user);
    return proyectRepository.save(proyect);
}
@Transactional(readOnly = true)
    public List<Proyect> findAll() {
    return proyectRepository.findAll();
    }

@Transactional(readOnly = true)
public Proyect findById(Long id) {
    return proyectRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Proyect no encontrado"));
}
@Transactional(readOnly = true)
public List<Proyect> findByUser(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    return proyectRepository.findByUsers(user);
    }
}
