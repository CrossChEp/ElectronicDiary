package com.diary.diary.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserAddModel {
    private String login;

    private String password;

    private String phoneNumber;

    private String email;

    private String name;

    private String surname;

    private String patronymic;

    public UserAddModel() {
    }
}
