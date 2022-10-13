package com.diary.diary.context;

import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.factory.RoleFactory;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;

public class UserContext {
    private UserRole userRole;

    public UserContext() throws UserNotFoundException {
        UserService userService = new UserService();
        userRole = RoleFactory.getUserRole(userService.getCurrentUser().getRole().getName());
    }
}
