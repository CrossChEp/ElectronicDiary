package com.diary.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", schema = "working_schema")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String login;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String phoneNumber;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @Getter @Setter
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    @Getter @Setter
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "classid")
    @Getter @Setter
    private ClassEntity userClass;



    public UserEntity() {
    }
}
