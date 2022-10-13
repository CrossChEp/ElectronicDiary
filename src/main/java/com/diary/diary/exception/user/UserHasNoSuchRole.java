package com.diary.diary.exception.user;

public class UserHasNoSuchRole extends RuntimeException {
    public UserHasNoSuchRole(String message) {
        super(message);
    }
}
