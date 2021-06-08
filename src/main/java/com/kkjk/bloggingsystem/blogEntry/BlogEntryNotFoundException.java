package com.kkjk.bloggingsystem.blogEntry;

import java.util.UUID;

public class BlogEntryNotFoundException extends RuntimeException{

    public BlogEntryNotFoundException(String id) {
        super("Blog entry not found for id = " + id);
    }
}
