package com.diary.diary.model.user;

import lombok.Getter;
import lombok.Setter;

public class UserAddModel {
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

    public UserAddModel() {
    }
}
