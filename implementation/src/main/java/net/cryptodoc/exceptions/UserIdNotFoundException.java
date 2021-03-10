package net.cryptodoc.exceptions;

public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(String id) {
        super("User with id '" + id + "' not found.");
    }
}
