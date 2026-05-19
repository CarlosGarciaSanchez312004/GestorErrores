package com.twindustry.gestorerrores.repository;

import com.twindustry.gestorerrores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNombreContainingIgnoreCase(String nombre);

    List<User> findByApellidoContainingIgnoreCase(String apellido);
}
