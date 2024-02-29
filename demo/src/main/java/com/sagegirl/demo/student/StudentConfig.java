package com.sagegirl.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student ivan = new Student(1L, "ivan", "ivan@email", LocalDate.of(2000, 10,10));
            Student michael = new Student(2L, "michael", "michael@email", LocalDate.of(2000, 10,10));
            studentRepository.saveAll(List.of(ivan, michael));
        };

    }

}
