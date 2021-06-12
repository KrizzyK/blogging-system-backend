package com.kkjk.bloggingsystem.blogEntry;

import com.kkjk.bloggingsystem.blogEntry.dto.BlogEntryRequestDto;
import com.kkjk.bloggingsystem.blogEntry.dto.BlogEntryResponseDto;
import com.kkjk.bloggingsystem.blogObject.BlogObjectEntity;
import com.kkjk.bloggingsystem.blogObject.BlogObjectService;
import com.kkjk.bloggingsystem.user.UserEntity;
import com.kkjk.bloggingsystem.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogEntryService {
    private final BlogEntryRepository repository;

    private final BlogObjectService blogObjectService;

    private final UserService userService;

    @Transactional
    public BlogEntryResponseDto getBlogEntryById(String entryUUID) {
        return BlogEntryFactory.entityToResponseDto(
                repository.findById(UUID.fromString(entryUUID))
                        .orElseThrow(() -> new BlogEntryNotFoundException(entryUUID)));
    }

    @Transactional
    public void incrementBlogEntryViewCount(String entryUUID) {
        BlogEntryEntity entity = repository.findById(UUID.fromString(entryUUID)).orElseThrow(() -> new BlogEntryNotFoundException(entryUUID));
        entity.setViewCount(entity.getViewCount() + 1);
        repository.save(entity);
    }


    @Transactional
    public UUID createEntry(BlogEntryRequestDto dto) {
        UserEntity author = userService.getCurrentUser();
        return repository.save( BlogEntryFactory.requestDtoToEntity(dto,author)).getId();
    }

    @Transactional
    public UUID updateBlogEntry(BlogEntryRequestDto dto, String entryUUID) {
        BlogEntryEntity entity = repository.findById(UUID.fromString(entryUUID)).orElseThrow(() -> new BlogEntryNotFoundException(entryUUID));

        // delete all previous BlogObjects
        List<BlogObjectEntity> previousBlogObjects =  entity.getBlogObjects();
        blogObjectService.deleteAllBlogObjects(previousBlogObjects);

        BlogEntryFactory.updateEntity(entity, dto);

        // update
        repository.save(entity);
        return entity.getId();

    }

    @Transactional
    public void deleteBlogEntry(String entryUUID) {
        BlogEntryEntity entity = repository.findById(UUID.fromString(entryUUID)).orElseThrow(() -> new BlogEntryNotFoundException(entryUUID));

        List<BlogObjectEntity> previousBlogObjects =  entity.getBlogObjects();
        blogObjectService.deleteAllBlogObjects(previousBlogObjects);

        repository.delete(entity);
    }

    @Transactional
    public List<BlogEntryResponseDto> getAllCurrentUserBlogEntries() {
        UserEntity userEntity = userService.getCurrentUser();
        return repository.findAllByAuthor(userEntity).stream().map(BlogEntryFactory::entityToResponseDto).collect(Collectors.toList());
    }
}
