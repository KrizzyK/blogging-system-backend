package com.kkjk.bloggingsystem.blogObject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogObjectService {

    private final BlogObjectRepository repository;

    public void deleteAllBlogObjects(List<BlogObjectEntity> blogObjects) {
        repository.deleteAll(blogObjects);
    }
}
