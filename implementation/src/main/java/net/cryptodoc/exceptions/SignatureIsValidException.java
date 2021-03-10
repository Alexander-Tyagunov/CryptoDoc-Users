package net.cryptodoc.exceptions;

public class SignatureIsValidException extends RuntimeException {

    public SignatureIsValidException(String email) {
        super("Signature for user with email '" + email + "' is valid.");
    }
}
