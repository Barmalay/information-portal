package com.example.diplom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
