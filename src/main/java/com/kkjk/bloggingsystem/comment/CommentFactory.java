package com.kkjk.bloggingsystem.comment;

import com.kkjk.bloggingsystem.comment.dto.CommentRequestDto;
import com.kkjk.bloggingsystem.comment.dto.CommentResponseDto;

import java.time.Instant;

public class CommentFactory {
    public static CommentResponseDto entityToResponse(CommentEntity commentEntity) {
        return CommentResponseDto.builder()
                .content(commentEntity.getContent())
                .createdDate(commentEntity.getCreatedDate())
                .username(commentEntity.getUsername())
                .build();
    }

    public static CommentEntity requestToEntity(CommentRequestDto commentRequestDto) {
        return CommentEntity.builder()
                .content(commentRequestDto.getContent())
                .createdDate(Instant.now())
                .username(commentRequestDto.getUsername())
                .build();
    }
}
