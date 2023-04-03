package com.mai.webApplication.services;

import com.mai.webApplication.models.Teacher;
import com.mai.webApplication.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationTeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public RegistrationTeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public void register(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
