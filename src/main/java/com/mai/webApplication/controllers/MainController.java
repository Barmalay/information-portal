package com.mai.webApplication.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String getMainPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentRole = authentication.getAuthorities().iterator().next().getAuthority();

        if(currentRole.equals("ROLE_TEACHER") || currentRole.equals("ROLE_ADMIN"))
            return "main/main-teacher";

        return "main/main-student";
    }
}
