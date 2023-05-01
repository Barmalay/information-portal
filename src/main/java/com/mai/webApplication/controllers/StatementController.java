package com.mai.webApplication.controllers;

import com.mai.webApplication.models.StatementForm;
import com.mai.webApplication.models.Teacher;
import com.mai.webApplication.models.User;
import com.mai.webApplication.repositories.UserRepository;
import com.mai.webApplication.services.StudentService;
import com.mai.webApplication.services.TeacherService;
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

@Controller
public class StatementController {

    private final UserRepository userRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public StatementController(UserRepository userRepository,
                               StudentService studentService,
                               TeacherService teacherService) {
        this.userRepository = userRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/choice-group")
    public String getChoiceGroup(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName()).get();

        if(currentUser.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("teachers", teacherService.findAll());
            return "statements/choice-group-admin";
        }

        ArrayList<String> groups = new ArrayList<>();
        ArrayList<String> subjects = new ArrayList<>();
        model.addAttribute("teachers", teacherService.findAll());
        for(Teacher teacher : currentUser.getTeachers()) {
            groups.add(teacher.getGroupStudent());
            subjects.add(teacher.getSubject());
        }
        model.addAttribute("groups", groups);
        model.addAttribute("subjects", subjects);

        return "statements/choice-group";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/statement")
    public String getCreateStatement(@ModelAttribute("StatementForm")StatementForm statementForm,
                                     @RequestParam("currentGroup") String group,
                                     @RequestParam("currentSubject") String subject,
                                     Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName()).get();

        model.addAttribute("group", group);
        model.addAttribute("subject", subject);
        model.addAttribute("number", "4324234");
        model.addAttribute("teacherName", currentUser.getTeachers().get(0).getFullName());
        model.addAttribute("students", studentService.loadStudentByGroup(group));

        return "statements/statement";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/statement")
    public String postCreateStatement(@RequestParam("studentId") int studentID) {
        System.out.println(studentID);
        return "";
    }
}
