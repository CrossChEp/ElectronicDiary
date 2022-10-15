package com.diary.diary.state;

import com.diary.diary.service.UserService;

public class TeacherRole extends StudentRole {
    public TeacherRole(UserService userService) {
        super(userService);
    }
}
