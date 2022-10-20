package com.diary.diary.model.school_class;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassGetByNumberModel {
    private int schoolNumber;

    private int number;

    private char letter;

    public ClassGetByNumberModel(int schoolNumber, int number, char letter) {
        this.schoolNumber = schoolNumber;
        this.number = number;
        this.letter = letter;
    }
}
