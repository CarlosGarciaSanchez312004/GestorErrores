package com.twindustry.gestorerrores.model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private User user;
    private Error error;
}
