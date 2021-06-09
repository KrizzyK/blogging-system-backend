package com.kkjk.bloggingsystem.comment;

import com.kkjk.bloggingsystem.blogEntry.BlogEntryEntity;
import com.kkjk.bloggingsystem.blogEntry.BlogEntryService;
import com.kkjk.bloggingsystem.comment.dto.CommentRequestDto;
import com.kkjk.bloggingsystem.comment.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogEntryService blogEntryService;

    public UUID createComment(String parentId, CommentRequestDto dto) {
        BlogEntryEntity blogEntryEntity = blogEntryService.getBlogEntryById(parentId);
        CommentEntity commentEntity = CommentFactory.requestToEntity(dto);

        blogEntryEntity.getComments().add(commentEntity);

        return commentRepository.save(commentEntity).getId();
    }

    public List<CommentResponseDto> getCommentsFromBlogEntry(String entryUUID) {
        return blogEntryService.getAllComments(entryUUID)
                .stream()
                .map(CommentFactory::entityToResponse)
                .collect(Collectors.toList());
    }

    public void deleteCommentById(String commentUUID) {
        commentRepository.deleteById(UUID.fromString(commentUUID));
    }
}
