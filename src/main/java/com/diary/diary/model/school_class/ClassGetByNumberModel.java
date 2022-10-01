package com.diary.diary.model.school_class;

import lombok.Getter;
import lombok.Setter;

public class ClassGetByNumberModel {
    @Getter @Setter
    private int schoolNumber;

    @Getter @Setter
    private int number;

    @Getter @Setter
    private char letter;

    public ClassGetByNumberModel(int schoolNumber, int number, char letter) {
        this.schoolNumber = schoolNumber;
        this.number = number;
        this.letter = letter;
    }
}
