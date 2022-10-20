package com.diary.diary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes", schema = "working_schema")
@Getter @Setter
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private int number;

    private char letter;

    @OneToMany(mappedBy = "userClass", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserEntity> students;

    @ManyToOne
    @JoinColumn(name = "schoolid")
    private SchoolEntity school;

    @OneToOne
    @JoinColumn(name = "timetableid")
    @JsonIgnore
    private TimetableEntity timetable;

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL)
    private List<HomeworkEntity> homework;

    public ClassEntity() {
    }
}
