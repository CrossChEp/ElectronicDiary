package com.diary.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "timetables")
public class TimetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    private long id;

    @Getter @Setter @ElementCollection
    private List<String> monday;

    @Getter @Setter @ElementCollection
    private List<String> tuesday;

    @Getter @Setter @ElementCollection
    private List<String> wednesday;

    @Getter @Setter @ElementCollection
    private List<String> thursday;

    @Getter @Setter @ElementCollection
    private List<String> friday;

    @Getter @Setter @ElementCollection
    private List<String> saturday;

    @Getter @Setter @ElementCollection
    private List<String> sunday;

    public TimetableEntity() {
    }
}
