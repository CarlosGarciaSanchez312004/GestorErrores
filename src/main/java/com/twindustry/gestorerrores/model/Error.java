package com.twindustry.gestorerrores.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "errors")
@Data
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String topic;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(columnDefinition = "TEXT")
    private String descriptionProblem;

    @Column(columnDefinition = "TEXT")
    private String solution;

    @Column(columnDefinition = "TEXT")
    private String descriptionSolution;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "reported_by")
    private User reportedBy;

    @ManyToOne
    @JoinColumn(name = "proyect_id")
    private Proyect proyect;
}