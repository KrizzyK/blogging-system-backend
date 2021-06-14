package com.kkjk.bloggingsystem.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePasswordRequestDto {
    private String oldPassword;
    private String newPassword;
}
