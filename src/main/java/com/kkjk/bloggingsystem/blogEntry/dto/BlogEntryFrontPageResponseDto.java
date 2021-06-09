package com.kkjk.bloggingsystem.blogEntry.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class BlogEntryFrontPageResponseDto {
    private String id;
    private String title;
    private Instant createdDate;
    private Integer viewCount;
}
