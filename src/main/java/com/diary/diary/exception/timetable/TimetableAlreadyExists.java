package com.diary.diary.exception.timetable;

public class TimetableAlreadyExists extends RuntimeException {
    public TimetableAlreadyExists(String message) {
        super(message);
    }
}
