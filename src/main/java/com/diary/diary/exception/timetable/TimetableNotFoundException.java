package com.diary.diary.exception.timetable;

public class TimetableNotFoundException extends RuntimeException {
    public TimetableNotFoundException(String message) {
        super(message);
    }
}
