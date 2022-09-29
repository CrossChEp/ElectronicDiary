package com.diary.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes", schema = "working_schema")
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private char letter;

    @OneToMany(mappedBy = "userClass")
    private List<UserEntity> students;

    @ManyToOne
    @JoinColumn(name = "schoolid")
    @Getter @Setter
    private SchoolEntity school;

    public ClassEntity() {
    }
}
