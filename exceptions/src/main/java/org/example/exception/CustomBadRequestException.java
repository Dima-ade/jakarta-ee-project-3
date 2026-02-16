package org.example.exception;

public class CustomBadRequestException extends Exception {

    public CustomBadRequestException() {
    }

    public CustomBadRequestException(String message) {
        super(message);
    }
}
