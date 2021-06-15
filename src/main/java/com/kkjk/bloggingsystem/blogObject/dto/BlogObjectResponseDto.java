package com.kkjk.bloggingsystem.blogObject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogObjectResponseDto {
    private String type;
    private String content;
    private int positionInBlogEntry;
}
