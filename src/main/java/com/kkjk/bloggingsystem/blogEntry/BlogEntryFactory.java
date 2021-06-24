package com.kkjk.bloggingsystem.blogEntry;

import com.kkjk.bloggingsystem.blogEntry.dto.*;
import com.kkjk.bloggingsystem.blogObject.BlogObjectEntity;
import com.kkjk.bloggingsystem.blogObject.BlogObjectFactory;
import com.kkjk.bloggingsystem.user.UserEntity;
import com.kkjk.bloggingsystem.user.UserFactory;

import java.time.Instant;
import java.util.stream.Collectors;

public class BlogEntryFactory {

    public static BlogEntryResponseDto entityToResponseDto(BlogEntryEntity entity) {
        return BlogEntryResponseDto.builder()
                .title(entity.getTitle())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .viewCount(entity.getViewCount())
                .author(UserFactory.entityToBasicInfoDto(entity.getAuthor()))
                .blogObjects(entity.getBlogObjects().stream().map(BlogObjectFactory::entityToResponseDto).collect(Collectors.toList()))
                .build();
    }

    public static BlogEntryEntity requestDtoToEntity(BlogEntryRequestDto dto, UserEntity author) {
        return BlogEntryEntity.builder()
                .title(dto.getTitle())
                .modifiedDate(Instant.now())
                .createdDate(Instant.now())
                .viewCount(0)
                .author(author)
                .blogObjects(dto.getBlogObjects().stream().map(BlogObjectFactory::requestDtoToEntity).collect(Collectors.toList()))
                .build();
    }

    public static BlogEntryEntity updateEntity(BlogEntryEntity entityToUpdate, BlogEntryRequestDto dto) {
        entityToUpdate.setBlogObjects(dto.getBlogObjects().stream().map(BlogObjectFactory::requestDtoToEntity).collect(Collectors.toList()));
        entityToUpdate.setTitle(dto.getTitle());
        entityToUpdate.setModifiedDate(Instant.now());
        return entityToUpdate;
    }

    public static BlogEntryFrontPageResponseDto entityToFrontPageResponseDto(BlogEntryEntity blogEntryEntity) {
        return BlogEntryFrontPageResponseDto.builder()
                .id(blogEntryEntity.getId().toString())
                .title(blogEntryEntity.getTitle())
                .createdDate(blogEntryEntity.getCreatedDate())
                .viewCount(blogEntryEntity.getViewCount())
                .build();
    }

    public static BlogEntryBasicInfoDto entityToBasicInfoDto(BlogEntryEntity entity) {
        return BlogEntryBasicInfoDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdDate(entity.getCreatedDate())
                .viewCount(entity.getViewCount())
                .build();
    }

    public static BlogEntryDetailedComponent entityToDetailedComponent(BlogEntryEntity entity) {
        BlogObjectEntity paragraph = entity.getBlogObjects().stream()
                .filter(obj ->
                    obj.getType().equals("paragraph")
                )
                .findFirst()
                .orElse(null);

        return BlogEntryDetailedComponent.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdDate(entity.getCreatedDate())
                .viewCount(entity.getViewCount())
                .author(UserFactory.entityToBasicInfoDto(entity.getAuthor()))
                .firstParagraph(paragraph != null ?  BlogObjectFactory.entityToResponseDto(paragraph).getContent() : null )
                .build();
    }
}
