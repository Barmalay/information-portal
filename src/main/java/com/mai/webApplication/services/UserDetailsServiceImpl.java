package com.mai.webApplication.services;

import com.mai.webApplication.models.User;
import com.mai.webApplication.repositories.UserRepository;
import com.mai.webApplication.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

//Сервис загружает имя пользователя из базы данных
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);

        if(user.isEmpty())
            throw new UsernameNotFoundException("Пользователь не найден!");
        return new UserDetailsImpl(user.get());
    }
}
