package com.example.webApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String LoginGet() {
        return "auth.html";
    }

    @PostMapping("/")
    public String LoginPost(@RequestParam String username,
                            @RequestParam String password) {

        return "";
    }
}
