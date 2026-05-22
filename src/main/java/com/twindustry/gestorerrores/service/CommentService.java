package com.twindustry.gestorerrores.service;

import com.twindustry.gestorerrores.model.User;
import com.twindustry.gestorerrores.repository.CommentRepository;
import com.twindustry.gestorerrores.repository.ErrorRepository;
import com.twindustry.gestorerrores.repository.UserRepository;
import com.twindustry.gestorerrores.model.Error;
import com.twindustry.gestorerrores.model.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ErrorRepository errorRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment createComment(Long userId, Long errorId, String commentText) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Error error = errorRepository.findById(errorId).orElseThrow(() -> new IllegalArgumentException("Error no encontrado"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setError(error);
        comment.setContent(commentText);
        return commentRepository.save(comment);
    }
    @Transactional(readOnly = true)
    public List<Comment> findByError(Long errorId) {
        Error error=errorRepository.findById(errorId).orElseThrow(() -> new IllegalArgumentException("Error no encontrado"));
        return commentRepository.findByError(error);
    }
}
