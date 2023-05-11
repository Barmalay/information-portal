package com.mai.webApplication.repositories;

import com.mai.webApplication.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Optional<Teacher> findBySubjectAndGroupStudent(String subject, String groupStudent);
    List<Teacher> findTeachersByGroupStudent(String groupStudent);
}
