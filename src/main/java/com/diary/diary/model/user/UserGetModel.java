package com.diary.diary.model.user;

import com.diary.diary.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class UserGetModel {
    private long id;

    private String login;

    private String name;

    private String surname;

    private String patronymic;

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
