package com.example.batchproject;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class BatchProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchProjectApplication.class, args);
    }

}
