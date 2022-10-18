package com.diary.diary.state;

import com.diary.diary.service.UserService;
import org.springframework.context.ApplicationContext;

public class AdminRole extends TeacherRole {
    public AdminRole(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
