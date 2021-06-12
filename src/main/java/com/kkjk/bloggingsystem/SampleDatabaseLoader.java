package com.kkjk.bloggingsystem;


import com.kkjk.bloggingsystem.role.RoleEntity;
import com.kkjk.bloggingsystem.role.RoleService;
import com.kkjk.bloggingsystem.user.UserEntity;
import com.kkjk.bloggingsystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
@Transactional
public class SampleDatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder bcryptEncoder;

    public void run(String... strings) {
        UserEntity admin = getAdminUser();
        userRepository.save(admin);
    }

    private UserEntity getAdminUser(){
        RoleEntity adminRole = roleService.getAdminRole();
        RoleEntity userRole = roleService.getUserRole();
        RoleEntity redactorRole = roleService.getRedactorRole();

        Set<RoleEntity> roleEntitySet = new HashSet<>();
        roleEntitySet.add(adminRole);
        roleEntitySet.add(userRole);
        roleEntitySet.add(redactorRole);

        return userRepository.findByEmail("admin@admin.com").orElse(
                UserEntity.builder()
                        .username("admin")
                        .email("admin@admin.com")
                        .password(bcryptEncoder.encode("admin"))
                        .roles(roleEntitySet)
                        .build()
        );
    }
}
