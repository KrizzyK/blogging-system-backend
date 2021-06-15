package com.kkjk.bloggingsystem.comment;

import com.kkjk.bloggingsystem.blogEntry.BlogEntryNotFoundException;
import com.kkjk.bloggingsystem.comment.dto.CommentRequestDto;
import com.kkjk.bloggingsystem.comment.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/blog")
public class CommentController {

    private final CommentService service;

    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    ResponseEntity<UUID> createComment(@RequestBody CommentRequestDto dto, @RequestParam String parentId) {
        try{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createComment(parentId, dto));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @RequestMapping(value = "/getCommentsFromBlogEntry", method = RequestMethod.GET)
    ResponseEntity<List<CommentResponseDto>> getCommentsFromBlogEntry(@RequestParam String entryUUID) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.getCommentsFromBlogEntry(entryUUID));
        } catch (BlogEntryNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteComment(@RequestParam String commentID) {
        try {
            service.deleteCommentById(commentID);
            return ResponseEntity.noContent().build();
        } catch (CommentNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
