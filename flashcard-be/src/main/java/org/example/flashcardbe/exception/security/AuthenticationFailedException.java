package org.example.flashcardbe.exception.security;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super("Authentication failed.");
    }
}
