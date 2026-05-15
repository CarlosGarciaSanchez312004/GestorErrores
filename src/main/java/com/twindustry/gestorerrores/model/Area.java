package com.twindustry.gestorerrores.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "areas")
@Data
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}