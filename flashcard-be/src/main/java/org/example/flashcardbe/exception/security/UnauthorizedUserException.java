package org.example.flashcardbe.exception.security;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException() {
        super("Unauthorized.");
    }
}
