package com.example.jpatutorial;

import com.example.jpatutorial.student.Student;
import com.example.jpatutorial.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaTutorialApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student maria = new Student(
                    "Maria",
                    "Jones",
                    "maria.jones@gmail.com",
                    21
            );

            Student maria2 = new Student(
                    "Maria",
                    "Potter",
                    "maria.potter@gmail.com",
                    25
            );

            Student ahmed = new Student(
                    "Ahmed",
                    "ALi",
                    "ahmed.ali@gmail.com",
                    18
            );

            studentRepository.saveAll(List.of(maria, maria2, ahmed));
            studentRepository
                    .findStudentByEmail("ahmed.ali@gmail.com")
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Email not exists.")
                    );

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeGreaterThanEqual(
                            "Maria",
                            18
                    ).forEach(System.out::println);

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeGreaterThanEqualNative(
                            "Maria",
                            18
                    ).forEach(System.out::println);

            studentRepository
                    .findStudentsByFirstNameEqualsAndAgeGreaterThanEqualNativeUsingParam(
                            "Maria",
                            18
                    ).forEach(System.out::println);

            System.out.println("Deleting Maria");
            System.out.println(studentRepository.deleteStudentById(2L));
        };
    }
}
