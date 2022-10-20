package com.diary.diary.model.timetable;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TimetableModel {
    private List<String> monday;
    private List<String> tuesday;
    private List<String> wednesday;
    private List<String> thursday;
    private List<String> friday;
    private List<String> saturday;
    private List<String> sunday;
}
