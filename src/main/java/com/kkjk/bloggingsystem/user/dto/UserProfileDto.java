package com.kkjk.bloggingsystem.user.dto;

import com.kkjk.bloggingsystem.role.RoleEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class UserProfileDto {

    private UUID id;

    private String username;
    private String email;

    private Set<RoleEntity> roles;

}
