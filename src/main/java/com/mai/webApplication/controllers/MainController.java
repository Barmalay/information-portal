package com.mai.webApplication.controllers;

import com.mai.webApplication.models.User;
import com.mai.webApplication.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main")
    public String getMainPage() {
        User currentUser = userService.getCurrentUser();
        String currentRole = currentUser.getRole();

        if(currentRole.equals("ROLE_TEACHER") || currentRole.equals("ROLE_ADMIN"))
            return "main/main-teacher";


        return "main/main-student";
    }
}
