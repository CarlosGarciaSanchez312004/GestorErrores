package com.twindustry.gestorerrores.model;

import java.time.LocalDateTime;

public class Error {
    private Long id;
    private String topic;
    private String area;
    private String descriptionProblem;
    private String solution;
    private String descriptionSolution;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User reportedBy;
    private Proyect proyect;
}
