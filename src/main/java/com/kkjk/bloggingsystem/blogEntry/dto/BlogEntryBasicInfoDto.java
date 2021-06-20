package com.kkjk.bloggingsystem.blogEntry.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class BlogEntryBasicInfoDto {
    private UUID id;
    private String title;
    private Integer viewCount;
    private Instant createdDate;
}
