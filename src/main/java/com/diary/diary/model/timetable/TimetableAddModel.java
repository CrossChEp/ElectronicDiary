package com.diary.diary.model.timetable;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TimetableAddModel {
    @Getter @Setter
    private List<String> monday;

    @Getter @Setter
    private List<String> tuesday;

    @Getter @Setter
    private List<String> wednesday;
    private List<String> thursday;

    @Getter @Setter
    private List<String> friday;

    @Getter @Setter
    private List<String> saturday;

    @Getter @Setter
    private List<String> sunday;
}
