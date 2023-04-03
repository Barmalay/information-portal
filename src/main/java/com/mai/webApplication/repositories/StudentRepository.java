package com.mai.webApplication.repositories;

import com.mai.webApplication.models.Student;
import com.mai.webApplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //Optional<Student> findByUsername(String username);
}
