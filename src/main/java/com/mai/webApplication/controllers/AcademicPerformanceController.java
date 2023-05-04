package com.mai.webApplication.controllers;

import com.mai.webApplication.models.User;
import com.mai.webApplication.repositories.UserRepository;
import com.mai.webApplication.services.StatementService;
import com.mai.webApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcademicPerformanceController {

    private final StatementService statementService;
    private final UserService userService;

    @Autowired
    public AcademicPerformanceController(StatementService statementService, UserService userService) {
        this.statementService = statementService;
        this.userService = userService;
    }


    @GetMapping("/academic-performance")
    public String getAcademicPerformance(Authentication authentication,
                                         Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("statements",statementService.findAllStudent(currentUser.getStudent()));

        return "statements/academic-performance";
    }
}
