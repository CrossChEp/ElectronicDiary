package com.diary.diary.state;

import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.user.UserGetModel;
import com.diary.diary.model.user.UserUpdateModel;
import com.diary.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Configurable
public class DefaultRole implements UserRole {

    @Autowired
    private UserService userService;

    @Override
    public UserGetModel getUser(long id) throws UserNotFoundException {
        return userService.getUser(id);
    }

    @Override
    public List<UserGetModel> getAllUsers() {
        return userService.getUsers();
    }

    @Override
    public UserEntity updateUser(UserUpdateModel newUserData) throws UserNotFoundException {
        return userService.updateUser(newUserData);
    }

    @Override
    public UserEntity deleteUser() throws UserNotFoundException {
        return userService.deleteUser();
    }
}
