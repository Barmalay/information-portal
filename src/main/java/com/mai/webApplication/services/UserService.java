package com.mai.webApplication.services;

import com.mai.webApplication.models.User;
import com.mai.webApplication.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User getCurrentUser() {
        Optional<User> currentUser = userRepository.findByUsername(getCurrentUserName());
        return currentUser.orElse(null);
    }
}
