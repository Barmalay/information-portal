package com.mai.webApplication.repositories;

import com.mai.webApplication.models.Student;
import com.mai.webApplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByGroupStudent(String groupStudent);

    @Query("SELECT DISTINCT s.groupStudent FROM Student s")
    List<String> findAllGroupNames();

    Student findStudentById(int id);
}
