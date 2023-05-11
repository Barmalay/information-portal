package com.mai.webApplication.controllers;

import com.ibm.icu.text.Transliterator;
import com.mai.webApplication.models.Statement;
import com.mai.webApplication.models.Teacher;
import com.mai.webApplication.models.User;
import com.mai.webApplication.services.StatementService;
import com.mai.webApplication.services.TeacherService;
import com.mai.webApplication.services.UserService;
import com.mai.webApplication.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class AcademicPerformanceController {

    private final StatementService statementService;
    private final UserService userService;
    private final TeacherService teacherService;
    private final Transliterator transliterator = Transliterator.getInstance("Russian-Latin/BGN");

    @Autowired
    public AcademicPerformanceController(StatementService statementService, UserService userService, TeacherService teacherService) {
        this.statementService = statementService;
        this.userService = userService;
        this.teacherService = teacherService;
    }


    @GetMapping("/academic-performance")
    public String getAcademicPerformance(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("statements",statementService.findAllStudentStatements(currentUser.getStudent()));

        return "statements/academic-performance";
    }

    @GetMapping("/statements/excel")
    public ResponseEntity<ByteArrayResource> downloadStudentsExcel() throws IOException {
        User currentUser = userService.getCurrentUser();
        List<Statement> statements = statementService.findAllStudentStatements(currentUser.getStudent());
        ByteArrayInputStream stream = ExcelHelper.statementsStudentToExcel(statements, currentUser.getStudent());

        String englishNameStudent = transliterator.transliterate(currentUser.getStudent().getFullName());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"Performance-of-student-(" + englishNameStudent  + ").xlsx\"");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        
        return ResponseEntity.ok().headers(headers).body(new ByteArrayResource(stream.readAllBytes()));
    }
}
