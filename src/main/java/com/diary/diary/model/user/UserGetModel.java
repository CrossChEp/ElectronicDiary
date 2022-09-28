package com.diary.diary.model.user;

import com.diary.diary.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

public class UserGetModel {
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String login;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @Getter @Setter
    private String patronymic;

    @Getter @Setter
    private String phoneNumber;

    public UserGetModel() {
    }

    public static UserGetModel toModel(UserEntity user) {
        UserGetModel model = new UserGetModel();
        ModelMapper mapper = new ModelMapper();
        mapper.map(user, model);
        return model;
    }
}
