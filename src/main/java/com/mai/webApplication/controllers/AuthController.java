package com.mai.webApplication.controllers;

import com.mai.webApplication.models.*;
import com.mai.webApplication.proxy.BlockChainProxy;
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
    private final StudentService studentService;
    private final UserValidator userValidator;

    private final BlockChainProxy blockChainProxy;

    @Autowired
    public AuthController(RegistrationUserService registrationUserService, RegistrationStudentService registrationStudentService, RegistrationTeacherService registrationTeacherService, StudentService studentService, UserValidator userValidator, BlockChainProxy blockChainProxy) {
        this.registrationUserService = registrationUserService;
        this.registrationStudentService = registrationStudentService;
        this.registrationTeacherService = registrationTeacherService;
        this.studentService = studentService;
        this.userValidator = userValidator;
        this.blockChainProxy = blockChainProxy;
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

        model.addAttribute("groups", studentService.findAllGroupNames());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("user") @Valid User user,
                                   BindingResult bindingResult,
                                   @ModelAttribute("student") Student student,
                                   @ModelAttribute("teacher") Teacher teacher,
                                   @ModelAttribute("selectedGroups[]")
                                       ArrayList<String> selectedGroups,
                                   Model model) {

        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("groups", studentService.findAllGroupNames());
            return "auth/registration";
        }
        user.setPublicKey(blockChainProxy.createAccount(user.getPublicKey()).getBody());
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
        }

        return "redirect:/auth/login";
    }
}
