package com.twindustry.gestorerrores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class GestorErroresApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestorErroresApplication.class, args);
    }
}

