package com.mai.webApplication.controllers;

import com.mai.webApplication.models.*;
import com.mai.webApplication.services.*;
import com.mai.webApplication.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationUserService registrationUserService;
    private final RegistrationStudentService registrationStudentService;
    private final RegistrationTeacherService registrationTeacherService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(RegistrationUserService registrationUserService, RegistrationStudentService registrationStudentService, RegistrationTeacherService registrationTeacherService, UserValidator userValidator) {
        this.registrationUserService = registrationUserService;
        this.registrationStudentService = registrationStudentService;
        this.registrationTeacherService = registrationTeacherService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String showRegistrationForm() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") User user,
                                      @ModelAttribute("student") Student student,
                                      @ModelAttribute("teacher") Teacher teacher,
                                      @ModelAttribute("selectedGroups")
                                          ArrayList<String> selectedGroups,
                                      Model model) {
        ArrayList<String> groups = new ArrayList<>();
        groups.add("М3О-409Б-19");
        groups.add("М3О-410Б-19");
        groups.add("М3О-407Б-19");
        groups.add("М3О-408Б-19");

        model.addAttribute("groups", groups);
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("user") @Valid User user,
                                   @ModelAttribute("student") Student student,
                                   @ModelAttribute("teacher") Teacher teacher,
                                   @ModelAttribute("selectedGroups")
                                       ArrayList<String> selectedGroups,
                                   BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors())
            return "auth/registration";

        registrationUserService.register(user);

        if(user.getRole().equals("ROLE_STUDENT")) {
            student.setUser(user);
            registrationStudentService.register(student);
        } else if(user.getRole().equals("ROLE_TEACHER")) {
            teacher.setUser(user);
            user.setTeachers(new ArrayList<>());
            for(String group : selectedGroups){
                teacher.setGroupStudent(group);
                user.getTeachers().add(teacher);
                registrationTeacherService.register(teacher);
            }
        } else {
            return "auth/registration";
        }

        return "redirect:/auth/login";
    }
}
