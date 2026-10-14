package com.lawproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.lawproject"})
public class LawProjectApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LawProjectApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(LawProjectApplication.class, args);
    }
}