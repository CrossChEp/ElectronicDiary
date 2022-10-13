package com.diary.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "marks", schema = "working_schema")
@Getter @Setter
public class MarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private int mark;
    private int weight;
    private String comment;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "subjectid")
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity student;

    public MarkEntity() {
    }
}
