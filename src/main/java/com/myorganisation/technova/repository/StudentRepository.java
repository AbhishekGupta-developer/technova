package com.myorganisation.technova.repository;

import com.myorganisation.technova.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom finder methods
    Optional<Student> findByPhone(String phone);
    List<Student> findByNameAndCourse(String name, String course);

    // Custom JPQL -> Java Persistence Query Language
    @Query("SELECT s FROM Student s WHERE s.course = :course")
    List<Student> getStudentsByCourse(@Param("course") String course);
}
