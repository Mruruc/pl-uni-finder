package com.mr.uruc.fu.sync.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FuSyncServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuSyncServiceApplication.class, args);
    }

}
