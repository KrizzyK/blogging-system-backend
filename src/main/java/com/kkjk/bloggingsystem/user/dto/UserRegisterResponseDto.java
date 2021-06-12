package com.kkjk.bloggingsystem.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Builder
@Data
public class UserRegisterResponseDto {
    private UUID id;
    private String username;
    private String email;
}
