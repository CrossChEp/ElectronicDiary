package com.diary.diary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "homeworks", schema = "working_schema")
@Getter @Setter
public class HomeworkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "subjectid")
    private SubjectEntity subject;
    @ManyToOne
    @JoinColumn(name = "classid")
    private ClassEntity schoolClass;
    @Getter @Setter
    private String content;
    private Date date;

    public HomeworkEntity() {
    }
}
