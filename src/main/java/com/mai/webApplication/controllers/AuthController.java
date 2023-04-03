package com.mai.webApplication.controllers;

import com.mai.webApplication.models.Student;
import com.mai.webApplication.models.Teacher;
import com.mai.webApplication.models.User;
import com.mai.webApplication.services.RegistrationStudentService;
import com.mai.webApplication.services.RegistrationTeacherService;
import com.mai.webApplication.services.RegistrationUserService;
import com.mai.webApplication.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                                      @ModelAttribute("teacher") Teacher teacher) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("user") @Valid User user,
                                   @ModelAttribute("student") Student student,
                                   @ModelAttribute("teacher") Teacher teacher,
                                   BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors())
            return "auth/registration";

        if(user.getRole().equals("ROLE_STUDENT")) {
            registrationStudentService.register(student);
        } else if(user.getRole().equals("ROLE_TEACHER")) {
            registrationTeacherService.register(teacher);
        } else {
            return "auth/registration";
        }

        registrationUserService.register(user);

        return "redirect:/auth/login";
    }
}
