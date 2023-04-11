package com.mai.webApplication.util;


import com.mai.webApplication.models.User;
import com.mai.webApplication.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserDetailsServiceImpl userDetailsServiceReg;

    @Autowired
    public UserValidator(UserDetailsServiceImpl userDetailsServiceReg) {
        this.userDetailsServiceReg = userDetailsServiceReg;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if(user.getPassword().length() < 4 || user.getPassword().isBlank())
            errors.rejectValue("password", "", "Пароль слишком маленький");

        try {
            userDetailsServiceReg.loadUserByUsername(user.getUsername());
        } catch (Exception e) {
            return;
        }
        errors.rejectValue("username", "", "Пользователь уже существует!");
    }
}
