package com.mruruc.fu.university.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UniversityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityServiceApplication.class, args);
    }

}
