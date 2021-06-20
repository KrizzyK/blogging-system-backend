package com.kkjk.bloggingsystem.blogEntry;

import com.kkjk.bloggingsystem.blogEntry.dto.BlogEntryBasicInfoDto;
import com.kkjk.bloggingsystem.blogEntry.dto.BlogEntryFrontPageResponseDto;
import com.kkjk.bloggingsystem.blogEntry.dto.BlogEntryRequestDto;
import com.kkjk.bloggingsystem.blogEntry.dto.BlogEntryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/blog")
public class BlogEntryController {

    private final BlogEntryService service;

    @RequestMapping(value = "/getFrontPage", method = RequestMethod.GET)
    ResponseEntity<Page<BlogEntryFrontPageResponseDto>> getFrontPage(
            @PageableDefault()
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdDate", direction = Sort.Direction.DESC)
            }) Pageable pageable) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.getBlogPage(pageable));
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

    @RequestMapping(value = "/getBlogEntryById", method = RequestMethod.GET)
    ResponseEntity<BlogEntryResponseDto> getBlogEntry(@RequestParam String entryUUID) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.getBlogEntryById(entryUUID));
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


    @RequestMapping(value = "/redactor/createBlogEntry", method = RequestMethod.POST)
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

    @RequestMapping(value = "/redactor/updateBlogEntry", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/redactor/deleteBlogEntry", method = RequestMethod.DELETE)
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

    @RequestMapping(value = "/redactor/getAllCurrentUserBlogEntries", method = RequestMethod.GET)
    ResponseEntity<List<BlogEntryBasicInfoDto>> getAllMyBlogEntries() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.getAllCurrentUserBlogEntries());
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
