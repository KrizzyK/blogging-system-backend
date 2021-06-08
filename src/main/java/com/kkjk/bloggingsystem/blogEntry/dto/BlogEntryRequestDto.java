package com.kkjk.bloggingsystem.blogEntry.dto;


import com.kkjk.bloggingsystem.blogObject.dto.BlogObjectRequestDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BlogEntryRequestDto {
    private String title;

    private List<BlogObjectRequestDto> blogObjects;
}
