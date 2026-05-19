package com.twindustry.gestorerrores.repository;

import com.twindustry.gestorerrores.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {

    Optional<Area> findByName(String name);

    boolean existsByName(String name);
}
