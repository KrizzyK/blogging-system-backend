package com.kkjk.bloggingsystem.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class UserBasicInfoDto {
    private UUID id;
    private String username;

    private Set<String> roles;
}
