package com.mai.webApplication.controllers;

import com.mai.webApplication.models.*;
import com.mai.webApplication.services.StatementService;
import com.mai.webApplication.services.StudentService;
import com.mai.webApplication.services.TeacherService;
import com.mai.webApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
public class StatementController {

    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final StatementService statementService;

    @Autowired
    public StatementController(UserService userService, StudentService studentService,
                               TeacherService teacherService, StatementService statementService) {
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.statementService = statementService;
    }


    @GetMapping("/choice-group")
    public String getChoiceGroup(Model model) {
        User currentUser = userService.getCurrentUser();

        if(currentUser.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("teachers", teacherService.findAll());
            return "statements/choice-group-admin";
        }

        List<Teacher> teachers = currentUser.getTeachers();
        //teachers.removeIf(teacher -> statementService.findAllTeacher(teacher) != null);
        model.addAttribute("teachers", teachers);

        return "statements/choice-group";
    }

    @GetMapping("/statement")
    public String getCreateStatement(@ModelAttribute("StatementForm")StatementForm statementForm,
                                     @RequestParam("currentGroup") String group,
                                     @RequestParam("currentSubject") String subject,
                                     Model model) {
        User currentUser = userService.getCurrentUser();

        model.addAttribute("group", group);
        model.addAttribute("subject", subject);
        model.addAttribute("number", "1");
        model.addAttribute("teacherName", currentUser.getTeachers().get(0).getFullName());
        model.addAttribute("students", studentService.loadStudentByGroup(group));

        return "statements/statement";
    }

    @PostMapping("/statement")
    public String postCreateStatement(@RequestParam("studentId") ArrayList<Integer> studentIds,
                                      @RequestParam("grade") ArrayList<String> ratings,
                                      @RequestParam("currentGroup") String group,
                                      @RequestParam("currentSubject") String subject) {

        Teacher teacher = teacherService.findBySubjectAndGroup(subject, group).get();
//        System.out.println(studentIds.toString());
//        System.out.println(ratings.toString());


        ArrayList<Student> students = new ArrayList<>();
        for(int id : studentIds)
            students.add(studentService.findStudentById(id));

        for(int i = 0; i < students.size(); i++) {
            Statement statement = new Statement();
            statement.setTeacher(teacher);
            statement.setNameSubject(subject);
            statement.setStudent(students.get(i));
            statement.setRating(ratings.get(i));
            statementService.register(statement);
        }

        return "main/main-teacher";
    }
}
