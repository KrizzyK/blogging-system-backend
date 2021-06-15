package com.kkjk.bloggingsystem.blogEntry;

public class BlogEntryNotFoundException extends RuntimeException{

    public BlogEntryNotFoundException(String id) {
        super("Blog entry not found for id = " + id);
    }
}
