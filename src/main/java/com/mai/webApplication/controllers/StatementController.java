package com.mai.webApplication.controllers;

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

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/choice_group")
    public String getChoiceGroup(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName()).get();

        ArrayList<String> groups = new ArrayList<>();
        ArrayList<String> subjects = new ArrayList<>();
        for(Teacher teacher : currentUser.getTeachers()) {
            groups.add(teacher.getGroupStudent());
            subjects.add(teacher.getSubject());
        }
        model.addAttribute("groups", groups);
        model.addAttribute("subjects", subjects);

        return "statements/choice_group";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/statement")
    public String getCreateStatement(@RequestParam("currentGroup") String group,
                                     @RequestParam("currentSubject") String subject,
                                     Model model) {
        model.addAttribute("group", group);
        model.addAttribute("subject", subject);
        model.addAttribute("number", "4324234");
        model.addAttribute("teacherName", teacherService.loadTeacherBySubjectAndGroupStudent(subject,group).get().getFullName());
        model.addAttribute("students", studentService.loadStudentByGroup(group));

        return "statements/statement";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/statement")
    public String postCreateStatement() {

        return "";
    }
}
