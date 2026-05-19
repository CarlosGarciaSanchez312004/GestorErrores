package com.twindustry.gestorerrores.repository;

import com.twindustry.gestorerrores.model.Area;
import com.twindustry.gestorerrores.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByName(String name);

    boolean existsByName(String name);

    List<Topic> findByArea(Area area);
}
