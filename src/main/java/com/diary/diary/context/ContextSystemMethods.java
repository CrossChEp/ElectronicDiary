package com.diary.diary.context;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.RoleEntity;
import com.diary.diary.entity.UserEntity;
import com.diary.diary.factory.RoleFactory;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.context.ApplicationContext;

public class ContextSystemMethods {
    public static UserRole getStateRoleOfUser(UserService userService, ApplicationContext applicationContext) {
        UserEntity currentUser = userService.getCurrentUser();
        String role = currentUser == null ? RoleNames.UNREGISTERED : userService.getCurrentUser().getRole().getName();
        return RoleFactory.getUserRole(role,
                applicationContext);
    }
}
