package com.twindustry.gestorerrores.controller;
import com.twindustry.gestorerrores.service.CommentService;
import com.twindustry.gestorerrores.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{userId}/{errorId}")
    public Comment create(@PathVariable Long userId, @PathVariable Long errorId, @RequestBody String commentText) {
        return commentService.createComment(userId, errorId, commentText);
    }
    @GetMapping("/find/{errorId}")
    public List<Comment> findByErrorId(@PathVariable Long errorId) {
        return commentService.findByError(errorId);
    }
}
