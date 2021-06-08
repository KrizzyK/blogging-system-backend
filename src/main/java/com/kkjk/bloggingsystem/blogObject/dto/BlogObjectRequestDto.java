package com.kkjk.bloggingsystem.blogObject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogObjectRequestDto {
    private String type;
    private String content;
    private int positionInBlogEntry;
}
