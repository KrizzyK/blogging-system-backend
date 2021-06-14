package com.kkjk.bloggingsystem.user;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String email) {
        super("Email " + email + " is already taken");
    }
}
