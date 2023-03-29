package com.mai.webApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String getMainPage() {
        return "main/main";
    }

    @GetMapping("/admin")
    public String getAdminPage () {
        return "main/admin";
    }
}
