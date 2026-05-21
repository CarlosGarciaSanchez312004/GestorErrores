package com.twindustry.gestorerrores.service;

import com.twindustry.gestorerrores.model.Role;
import com.twindustry.gestorerrores.model.User;
import com.twindustry.gestorerrores.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public User createUser(String nombre, String apellido, String email, String password, String picture, Role role) {
    User user = new User();
    user.setNombre(nombre);
    user.setApellido(apellido);
    user.setEmail(email);
    user.setPassword(password);
    user.setPicture(picture);
    user.setRole(role);
    user.setEnable(true);
    return userRepository.save(user);
    }
    @Transactional
    public User enableUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        user.setEnable(true);
        return userRepository.save(user);
    }
    @Transactional
    public User disableUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        user.setEnable(false);
        return userRepository.save(user);
    }
    @Transactional (readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Transactional (readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
    @Transactional (readOnly = true)
    public List<User> findByName(String nombre) {
        return userRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
