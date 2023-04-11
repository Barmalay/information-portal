package com.mai.webApplication.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StatementController {

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/create_statement")
    public String getCreateStatement() {

        return "";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/create_statement")
    public String postCreateStatement() {

        return "";
    }
}
