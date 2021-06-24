package com.kkjk.bloggingsystem.blogEntry.dto;


import com.kkjk.bloggingsystem.user.dto.UserBasicInfoDto;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class BlogEntryDetailedComponent {
    private UUID id;
    private String title;
    private Integer viewCount;
    private Instant createdDate;


    private UserBasicInfoDto author;

    private String firstParagraph;
}
