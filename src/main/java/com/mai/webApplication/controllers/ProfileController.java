package com.mai.webApplication.controllers;

import com.mai.webApplication.models.User;
import com.mai.webApplication.services.TeacherService;
import com.mai.webApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final TeacherService teacherService;
    private final UserService userService;

    @Autowired
    public ProfileController(TeacherService teacherService, UserService userService) {
        this.teacherService = teacherService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getAccountPage(Model model) {
        User currentUser = userService.getCurrentUser();

        if(currentUser.getRole().equals("ROLE_STUDENT")) {
            model.addAttribute("student", currentUser.getStudent());
            return "profile/profile-student";
        }

        model.addAttribute("teacher", currentUser.getTeachers().get(0));

        if(currentUser.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("teachers", teacherService.findAll());
        } else {
            model.addAttribute("teachers", currentUser.getTeachers());
        }

        return "profile/profile-teacher";
    }
}
