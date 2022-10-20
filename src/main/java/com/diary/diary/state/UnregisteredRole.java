package com.diary.diary.state;

import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.user.UserAlreadyExistsException;
import com.diary.diary.model.user.UserAddModel;
import com.diary.diary.model.user.UserGetModel;
import com.diary.diary.service.UserService;
import org.springframework.context.ApplicationContext;

public class UnregisteredRole implements UserRole {

    private UserService userService;

    public UnregisteredRole() {
    }

    public UnregisteredRole(ApplicationContext applicationContext) {
        this.userService = new UserService(applicationContext);
    }

    @Override
    public UserGetModel register(UserAddModel userData) {
        return userService.addUser(userData);
    }
}
