package com.kkjk.bloggingsystem.user.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UserLoginRequestDto {

    private String email;
    private String password;

}