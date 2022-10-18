package com.diary.diary.exception.subject;

public class SubjectAlreadyExistsException extends RuntimeException {
    public SubjectAlreadyExistsException(String message) {
        super(message);
    }
}
