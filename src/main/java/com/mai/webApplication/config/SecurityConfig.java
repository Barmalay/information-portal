package com.mai.webApplication.config;

import com.mai.webApplication.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    //Настраиваем Spring Security и авторизацию
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()                                //Все запросы проходят чере авторизацию
                .antMatchers("/auth/login","/auth/registration","/error") //Разрешаем все пользователям доступ на эти страницы
                .permitAll()
                .anyRequest().authenticated()                   //Вход на дургие старницы только для авторизированного пользоваьеля
                .and()
                .formLogin().loginPage("/auth/login")           //Страница для входа
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/main",true)
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
    }

    //Настраивает аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(getPasswordEncoder());
    }

    //Даем понять Spring Security что пароль бзе шифрования.
    @Bean
    public PasswordEncoder getPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
