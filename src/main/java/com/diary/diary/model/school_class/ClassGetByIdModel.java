package com.diary.diary.model.school_class;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassGetByIdModel {
    private long schoolId;
    private int number;
    private char letter;

    public ClassGetByIdModel(long schoolId, int number, char letter) {
        this.schoolId = schoolId;
        this.number = number;
        this.letter = letter;
    }
}
