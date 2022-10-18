package com.diary.diary.factory;

import com.diary.diary.config.RoleNames;
import com.diary.diary.service.UserService;
import com.diary.diary.state.*;
import org.springframework.context.ApplicationContext;

public class RoleFactory {
    private static UserRole userRole;

    public static UserRole getUserRole(String roleName, ApplicationContext applicationContext) {
        if(userRole == null) {
            buildUserRole(roleName, applicationContext);
        }
        return userRole;
    }

    private static void buildUserRole(String roleString, ApplicationContext applicationContext) {
        switch (roleString) {
            case RoleNames.DEFAULT -> userRole = new DefaultRole();
            case RoleNames.STUDENT -> userRole = new StudentRole(applicationContext);
            case RoleNames.TEACHER -> userRole = new TeacherRole(applicationContext);
            case RoleNames.ADMIN -> userRole = new AdminRole(applicationContext);
        }
    }
}
