package com.mai.webApplication.controllers;

import com.mai.webApplication.models.PasswordForm;
import com.mai.webApplication.models.User;
import com.mai.webApplication.services.TeacherService;
import com.mai.webApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProfileController {

    private final TeacherService teacherService;
    private final UserService userService;

    @Autowired
    public ProfileController(TeacherService teacherService, UserService userService) {
        this.teacherService = teacherService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getAccountPage(Model model) {
        User currentUser = userService.getCurrentUser();

        if(currentUser.getRole().equals("ROLE_STUDENT")) {
            model.addAttribute("student", currentUser.getStudent());
            return "profile/profile-student";
        }

        model.addAttribute("teacher", currentUser.getTeachers().get(0));

        if(currentUser.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("teachers", teacherService.findAll());
        } else {
            model.addAttribute("teachers", currentUser.getTeachers());
        }

        return "profile/profile-teacher";
    }

    @GetMapping("/profile/change-password")
    String showChangePasswordForm(@ModelAttribute("passwordForm") PasswordForm form) {
        return "profile/change-password";
    }

    @PostMapping("/profile/change-password")
    public String processChangePasswordForm(@ModelAttribute("passwordForm") @Valid PasswordForm form,
                                            BindingResult bindingResult,
                                            Model model) {

        if (bindingResult.hasErrors()) {
            return "profile/change-password";
        }

        if (!form.passwordsMatch()) {
            bindingResult.rejectValue("newPasswordConfirm", "", "Пароли не совпадают");
            return "profile/change-password";
        }

        User user = userService.getCurrentUser();

        if (!userService.passwordsMatch(form.getCurrentPassword(), user.getPassword())) {
            bindingResult.rejectValue("currentPassword", "", "Неверный текущий пароль");
            return "profile/change-password";
        }

        if(form.oldAndNewPasswordsMatch()) {
            bindingResult.rejectValue("newPassword", "", "Новый пароль должен отличаться от предыдущего");
            return "profile/change-password";
        }

        userService.updatePassword(user, form.getNewPassword());

        model.addAttribute("successMessage", "Пароль успешно изменен");
        return "redirect:/profile";
    }
}
