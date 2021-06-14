package com.kkjk.bloggingsystem.blogEntry.dto;

import com.kkjk.bloggingsystem.blogObject.dto.BlogObjectResponseDto;
import com.kkjk.bloggingsystem.user.dto.UserBasicInfoDto;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class BlogEntryResponseDto {
    private String title;
    private Instant createdDate;
    private Instant modifiedDate;
    private Integer viewCount;

    private UserBasicInfoDto author;

    private List<BlogObjectResponseDto> blogObjects;
}