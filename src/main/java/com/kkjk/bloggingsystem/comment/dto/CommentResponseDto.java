package com.kkjk.bloggingsystem.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CommentResponseDto {
    private String username;
    private String content;
    private Timestamp createdDate;
}
