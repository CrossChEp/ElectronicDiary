package com.diary.diary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schools", schema = "working_schema")
public class SchoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private long number;

    @OneToMany(mappedBy = "school")
    @Getter @Setter
    private List<UserEntity> students;

    @OneToMany(mappedBy = "school")
    @Getter @Setter
    @JsonIgnore
    private List<ClassEntity> classes;
}
