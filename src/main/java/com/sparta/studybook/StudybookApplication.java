package com.sparta.studybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StudybookApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudybookApplication.class, args);
    }

}
