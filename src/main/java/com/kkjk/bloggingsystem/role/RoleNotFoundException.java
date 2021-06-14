package com.kkjk.bloggingsystem.role;


public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String role) {
        super("Role " + role + " not found");
    }
}
