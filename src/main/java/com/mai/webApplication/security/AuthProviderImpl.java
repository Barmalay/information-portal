package com.mai.webApplication.security;

import com.mai.webApplication.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

//Поиск логина и пароля в базе данных для аутентификации пользователей
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AuthProviderImpl(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();
        if(!password.equals(userDetails.getPassword()))
            throw new BadCredentialsException("неверный пароль");

        return new UsernamePasswordAuthenticationToken(userDetails, password,
                Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
