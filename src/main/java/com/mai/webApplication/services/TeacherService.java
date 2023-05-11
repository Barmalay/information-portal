package com.mai.webApplication.services;

import com.mai.webApplication.models.Teacher;
import com.mai.webApplication.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Optional<Teacher> findBySubjectAndGroup(String subject, String groupStudent) {
        return teacherRepository.findBySubjectAndGroupStudent(subject, groupStudent);
    }

    public List<Teacher> findByTeacherByGroup(String groupStudent) {
        return teacherRepository.findTeachersByGroupStudent(groupStudent);
    }

    public List<Teacher> findAll(){
        List<Teacher> teachers = teacherRepository.findAll();
        teachers.removeIf(teacher -> teacher.getGroupStudent() == null || teacher.getSubject() == null);
        return teachers;
    }

    public String fullNameTypeControl(String shortTC) {
        return switch (shortTC) {
            case ("Э") -> "Экзамен(" + shortTC + ")";
            case ("Зо") -> "Зачет с оценкой(" + shortTC + ")";
            case ("Зч") -> "Зачет(" + shortTC + ")";
            case ("КР") -> "Курсовая работа(" + shortTC + ")";
            default -> "-";
        };
    }
}
