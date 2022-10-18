package com.diary.diary.state;

import com.diary.diary.service.UserService;
import org.springframework.context.ApplicationContext;

public class TeacherRole extends StudentRole {
    public TeacherRole(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
