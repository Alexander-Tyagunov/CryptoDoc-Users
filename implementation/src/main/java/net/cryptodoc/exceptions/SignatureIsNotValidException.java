package net.cryptodoc.exceptions;

public class SignatureIsNotValidException extends RuntimeException {

    public SignatureIsNotValidException(String email) {
        super("Signature for user with email '" + email + "' is not ready or not valid.");
    }
}
