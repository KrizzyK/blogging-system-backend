package com.kkjk.bloggingsystem.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CommentResponseDto {
    private String id;
    private String username;
    private String content;
    private Instant createdDate;
}
