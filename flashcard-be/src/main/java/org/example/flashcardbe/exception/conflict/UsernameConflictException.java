package org.example.flashcardbe.exception.conflict;

public class UsernameConflictException extends ConflictException {
    public UsernameConflictException() {
        super("Username already exists!");
    }
}
