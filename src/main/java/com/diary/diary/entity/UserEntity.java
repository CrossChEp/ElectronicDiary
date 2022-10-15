package com.diary.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", schema = "working_schema")
@Getter @Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String login;

    private String password;

    private String phoneNumber;

    private String email;

    private String name;

    private String surname;

    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "classid")
    private ClassEntity userClass;

    @ManyToOne
    @JoinColumn(name = "schoolid")
    private SchoolEntity school;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<MarkEntity> marks;


    public UserEntity() {
    }
}
