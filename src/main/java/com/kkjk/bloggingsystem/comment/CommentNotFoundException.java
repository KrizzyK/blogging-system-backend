package com.kkjk.bloggingsystem.comment;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String id) {
        super("Comment not found for id = " + id);
    }
}
