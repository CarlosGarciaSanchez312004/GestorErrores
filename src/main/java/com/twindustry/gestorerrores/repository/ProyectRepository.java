package com.twindustry.gestorerrores.repository;


import com.twindustry.gestorerrores.model.Proyect;
import com.twindustry.gestorerrores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProyectRepository extends JpaRepository<Proyect, Long> {

    Optional<Proyect> findByName(String name);

    List<Proyect> findByUsers(User user);
}
