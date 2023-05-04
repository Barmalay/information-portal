package com.mai.webApplication.services;

import com.mai.webApplication.models.Student;
import com.mai.webApplication.models.Teacher;
import com.mai.webApplication.repositories.StudentRepository;
import com.mai.webApplication.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> loadStudentByGroup(String groupStudent) {
        return studentRepository.findByGroupStudent(groupStudent);
    }

    public List<String> findAllGroupNames() {
        return studentRepository.findAllGroupNames();
    }

    public Student findStudentById(int id) {
        return studentRepository.findStudentById(id);
    }
}
