package com.twindustry.gestorerrores.repository;

import com.twindustry.gestorerrores.model.Comment;
import com.twindustry.gestorerrores.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByError(Error error);

    Long countByError(Error error);
}