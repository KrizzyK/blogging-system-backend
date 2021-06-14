package com.kkjk.bloggingsystem.security;

import com.kkjk.bloggingsystem.role.RoleService;
import com.kkjk.bloggingsystem.user.UserRepository;
import com.kkjk.bloggingsystem.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository, RoleService roleService, PasswordEncoder encoder) {
        return new UserService(userRepository, roleService, encoder);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

}
