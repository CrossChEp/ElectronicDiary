package com.diary.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects", schema = "working_schema")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String name;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<HomeworkEntity> homeworks;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<MarkEntity> marks;

    public SubjectEntity() {
    }
}
