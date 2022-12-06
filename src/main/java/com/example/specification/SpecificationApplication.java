package com.example.specification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpecificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpecificationApplication.class, args);
    }
}