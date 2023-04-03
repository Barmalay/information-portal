package com.mai.webApplication.services;

import com.mai.webApplication.models.Student;
import com.mai.webApplication.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationStudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public RegistrationStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void register(Student student) {
        studentRepository.save(student);
    }
}
