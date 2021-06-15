package com.kkjk.bloggingsystem.user;

import com.kkjk.bloggingsystem.role.RoleEntity;
import com.kkjk.bloggingsystem.user.dto.UserBasicInfoDto;
import com.kkjk.bloggingsystem.user.dto.UserProfileDto;
import com.kkjk.bloggingsystem.user.dto.UserRegisterRequestDto;
import com.kkjk.bloggingsystem.user.dto.UserRegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@RequiredArgsConstructor
public class UserFactory {


    public static UserEntity registerDtoToEntity(UserRegisterRequestDto dto, Set<RoleEntity> roles, PasswordEncoder encoder) {
        return UserEntity.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .roles(roles)
                .build();
    }
    public static UserRegisterResponseDto entityToRegisterResponseDto(UserEntity entity) {
        return UserRegisterResponseDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .roles(entity.getRoles())
                .build();
    }

    public static UserBasicInfoDto entityToBasicInfoDto(UserEntity entity) {
        return UserBasicInfoDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }

    public static UserProfileDto entityToProfileDto(UserEntity entity) {
        return UserProfileDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .roles(entity.getRoles())
                .build();
    }

}
