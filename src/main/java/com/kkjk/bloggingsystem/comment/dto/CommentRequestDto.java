package com.kkjk.bloggingsystem.comment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequestDto {
    private String username;
    private String content;
}
