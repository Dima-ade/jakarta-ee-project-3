package org.example.exception;

public class MissingItemException extends Exception {

    public MissingItemException() {
    }

    public MissingItemException(String message) {
        super(message);
    }
}
