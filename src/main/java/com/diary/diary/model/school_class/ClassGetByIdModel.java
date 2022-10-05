package com.diary.diary.model.school_class;

import lombok.Getter;
import lombok.Setter;

public class ClassGetByIdModel {
    @Getter @Setter
    private long schoolId;

    @Getter @Setter
    private int number;

    @Getter @Setter
    private char letter;

    public ClassGetByIdModel(long schoolId, int number, char letter) {
        this.schoolId = schoolId;
        this.number = number;
        this.letter = letter;
    }
}
