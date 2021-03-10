package net.cryptodoc.exceptions;

public class NoValuePresentException extends RuntimeException {

    public NoValuePresentException() {
        super("No value present");
    }
}
