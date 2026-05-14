package com.twindustry.gestorerrores.model;

import java.time.LocalDateTime;

public class Attachment {
    private Long id;
    private String filename;
    private String fileType;
    private String filepath;
    private LocalDateTime createdAt;
    private User uploadedBy;
    private Error error;
}
