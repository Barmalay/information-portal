package com.mai.webApplication.controllers;

import com.mai.webApplication.models.User;
import com.mai.webApplication.repositories.UserRepository;
import com.mai.webApplication.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final TeacherService teacherService;

    @Autowired
    public ProfileController(UserRepository userRepository, TeacherService teacherService) {
        this.userRepository = userRepository;
        this.teacherService = teacherService;
    }

    @GetMapping("/profile")
    public String getAccountPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName()).get();

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
