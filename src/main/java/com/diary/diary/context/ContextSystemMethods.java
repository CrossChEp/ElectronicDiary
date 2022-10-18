package com.diary.diary.context;

import com.diary.diary.entity.RoleEntity;
import com.diary.diary.factory.RoleFactory;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.context.ApplicationContext;

public class ContextSystemMethods {
    public static UserRole getStateRoleOfUser(UserService userService, ApplicationContext applicationContext) {
        String role = userService.getCurrentUser().getRole().getName();
        return RoleFactory.getUserRole(role,
                applicationContext);
    }
}
