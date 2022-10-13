package com.diary.diary.factory;

import com.diary.diary.config.RoleNames;
import com.diary.diary.state.*;

public class RoleFactory {
    private static UserRole userRole;

    public static UserRole getUserRole(String roleName) {
        if(userRole == null) {
            userRole = buildUserRole(roleName);
        }
        return userRole;
    }

    private static UserRole buildUserRole(String roleString) {
        switch (roleString) {
            case RoleNames.DEFAULT -> userRole = new DefaultRole();
            case RoleNames.STUDENT -> userRole = new StudentRole();
            case RoleNames.TEACHER -> userRole = new TeacherRole();
            case RoleNames.ADMIN -> userRole = new AdminRole();
        }
        return userRole;
    }
}
