package com.diary.diary.model.mark;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DateAndSubjectModel {
    private String date;
    private String subjectName;

    public DateAndSubjectModel() {
    }

    public DateAndSubjectModel(String date, String subjectName) {
        this.date = date;
        this.subjectName = subjectName;
    }
}
