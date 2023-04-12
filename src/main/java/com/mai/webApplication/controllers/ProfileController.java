package com.mai.webApplication.controllers;

import com.mai.webApplication.models.Teacher;
import com.mai.webApplication.models.User;
import com.mai.webApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class ProfileController {

    private final UserRepository userRepository;

    @Autowired
    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String getAccountPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName()).get();
        model.addAttribute("role", currentUser.getRole());

        outputInf(model, currentUser);

        return "profile/profile";
    }

    private void outputInf(Model model, User currentUser) {
        if(currentUser.getStudent() != null) {
            model.addAttribute("firstName", currentUser.getStudent().getFirstName());
            model.addAttribute("surName", currentUser.getStudent().getSurName());
            model.addAttribute("fatherLand", currentUser.getStudent().getFatherLand());
            model.addAttribute("groupStudent", currentUser.getStudent().getGroupStudent());
        } else {
            model.addAttribute("firstName", currentUser.getTeachers().get(0).getFirstName());
            model.addAttribute("surName", currentUser.getTeachers().get(0).getSurName());
            model.addAttribute("fatherLand", currentUser.getTeachers().get(0).getFatherLand());

            ArrayList<String> groups = new ArrayList<>();
            ArrayList<String> subjects = new ArrayList<>();
            for(Teacher teacher : currentUser.getTeachers()) {
                groups.add(teacher.getGroupStudent());
                subjects.add(teacher.getSubject());
            }
            model.addAttribute("groups", groups);
            model.addAttribute("subjects", subjects);
        }
    }
}
