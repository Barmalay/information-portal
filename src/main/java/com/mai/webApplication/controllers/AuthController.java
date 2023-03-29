package com.mai.webApplication.controllers;

import com.mai.webApplication.models.User;
import com.mai.webApplication.services.RegistrationService;
import com.mai.webApplication.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("user") @Valid User user,
                                   BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors())
            return "auth/registration";

        registrationService.register(user);

        return "redirect:/auth/login";
    }
}
