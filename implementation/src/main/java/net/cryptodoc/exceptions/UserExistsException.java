package net.cryptodoc.exceptions;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String email) {
        super("User with " + email + " already exists");
    }
}
