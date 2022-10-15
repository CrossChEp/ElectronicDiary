package com.diary.diary.state;

import com.diary.diary.service.UserService;

public class AdminRole extends TeacherRole {

    public AdminRole(UserService userService) {
        super(userService);
    }
}
