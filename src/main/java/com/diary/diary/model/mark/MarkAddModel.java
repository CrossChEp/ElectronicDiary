package com.diary.diary.model.mark;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class MarkAddModel {
    private int mark;
    private int weight;
    private String subjectName;
    private long userId;
    private String comment;
    private Date date;
}
