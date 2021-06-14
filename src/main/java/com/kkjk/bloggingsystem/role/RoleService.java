package com.kkjk.bloggingsystem.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service(value = "roleService")
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity getUserRole(){
        Optional<RoleEntity> role = roleRepository.findByName("USER");
        if(role.isPresent()) return role.get();

        RoleEntity newRole = RoleEntity.builder()
                .name("USER")
                .build();
        roleRepository.save(newRole);
        return newRole;
    }

    public RoleEntity getAdminRole(){
        Optional<RoleEntity> role = roleRepository.findByName("ADMIN");
        if(role.isPresent()) return role.get();

        RoleEntity newRole = RoleEntity.builder()
                .name("ADMIN")
                .build();
        roleRepository.save(newRole);
        return newRole;
    }

    public RoleEntity getRedactorRole(){
        Optional<RoleEntity> role = roleRepository.findByName("REDACTOR");
        if(role.isPresent()) return role.get();

        RoleEntity newRole = RoleEntity.builder()
                .name("REDACTOR")
                .build();
        roleRepository.save(newRole);
        return newRole;
    }

    public RoleEntity findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
    }
}
