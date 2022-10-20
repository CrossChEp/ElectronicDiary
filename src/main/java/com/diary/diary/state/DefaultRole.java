package com.diary.diary.state;

import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.user.UserAlreadyExistsException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.user.UserAddModel;
import com.diary.diary.model.user.UserGetModel;
import com.diary.diary.model.user.UserUpdateModel;
import com.diary.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Configurable
public class DefaultRole extends UnregisteredRole {

    private UserService userService;

    public DefaultRole() {
    }

    public DefaultRole(ApplicationContext applicationContext) {
        this.userService = new UserService(applicationContext);
    }

    @Override
    public UserGetModel getUser(long id) throws UserNotFoundException {
        return userService.getUser(id);
    }

    @Override
    public List<UserGetModel> getAllUsers() {
        return userService.getUsers();
    }

    @Override
    public UserGetModel updateUser(UserUpdateModel newUserData) throws UserNotFoundException {
        return userService.updateUser(newUserData);
    }

    @Override
    public UserGetModel deleteUser() throws UserNotFoundException {
        return userService.deleteUser();
    }
}
