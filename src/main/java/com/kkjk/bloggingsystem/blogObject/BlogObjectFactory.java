package com.kkjk.bloggingsystem.blogObject;

import com.kkjk.bloggingsystem.blogObject.dto.BlogObjectRequestDto;
import com.kkjk.bloggingsystem.blogObject.dto.BlogObjectResponseDto;

public class BlogObjectFactory {
    public static BlogObjectResponseDto entityToResponseDto(BlogObjectEntity entity) {
        return BlogObjectResponseDto.builder()
                .content(entity.getContent())
                .positionInBlogEntry(entity.getPositionInBlogEntry())
                .type(entity.getType())
                .build();
    }

    public static BlogObjectEntity requestDtoToEntity(BlogObjectRequestDto dto) {
        return BlogObjectEntity.builder()
                .content(dto.getContent())
                .positionInBlogEntry(dto.getPositionInBlogEntry())
                .type(dto.getType())
                .build();
    }
}
