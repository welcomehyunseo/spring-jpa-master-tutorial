package com.example.jpatutorial.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("select s from Student s where s.firstName = ?1 and s.age >= ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeGreaterThanEqual(String firstName, Integer age);

    /**
     * This is only for postgres or the other database that can be used this query.
     */
    @Query(
            value = "select * from Student where first_name = ?1 and age >= ?2",
            nativeQuery = true
    )
    List<Student> findStudentsByFirstNameEqualsAndAgeGreaterThanEqualNative(String firstName, Integer age);

    @Query(
            value = "select * from Student where first_name = :firstName and age >= :age",
            nativeQuery = true
    )
    List<Student> findStudentsByFirstNameEqualsAndAgeGreaterThanEqualNativeUsingParam(
            @Param("firstName") String firstName,
            @Param("age") Integer age
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.id = ?1")
    int deleteStudentById(Long id);
}

