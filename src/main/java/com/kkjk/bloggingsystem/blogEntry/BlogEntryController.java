package com.kkjk.bloggingsystem.blogEntry;


import com.kkjk.bloggingsystem.blogEntry.dto.BlogEntryRequestDto;
import com.kkjk.bloggingsystem.blogEntry.dto.BlogEntryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/blog")
public class BlogEntryController {

    private final BlogEntryService service;

    @RequestMapping(value = "/getBlogEntryById", method = RequestMethod.GET)
    ResponseEntity<BlogEntryResponseDto> getBlogEntry(@RequestParam String entryUUID) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.getBlogEntryResponseById(entryUUID));
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


    @RequestMapping(value = "/createBlogEntry", method = RequestMethod.POST)
    ResponseEntity<UUID> createEntry(@RequestBody BlogEntryRequestDto dto) {
        try{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(service.createEntry(dto));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }


    @RequestMapping(value = "/incrementBlogEntryViewCount", method = RequestMethod.GET)
    ResponseEntity<Void> incrementBlogEntryViewCount(@RequestParam String entryUUID) {
        try {
            service.incrementBlogEntryViewCount(entryUUID);
            return ResponseEntity
                    .ok().build();
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

    @RequestMapping(value = "/updateBlogEntry", method = RequestMethod.PUT)
    ResponseEntity<UUID> updateBlogEntry(@RequestBody BlogEntryRequestDto dto, @RequestParam String entryUUID) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.updateBlogEntry(dto, entryUUID));
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

    @RequestMapping(value = "/deleteBlogEntry", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteBlogEntry(@RequestParam String entryUUID) {
        try {
            service.deleteBlogEntry(entryUUID);
            return ResponseEntity.noContent().build();
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




}
