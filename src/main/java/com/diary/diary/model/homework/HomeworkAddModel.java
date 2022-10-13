package com.diary.diary.model.homework;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class HomeworkAddModel {
    private long classId;
    private long subjectId;
    private String content;
    private Date date;
}
