package com.diary.diary.context;

import com.diary.diary.controller.ControllerServiceMethods;
import com.diary.diary.model.school.SchoolGetModel;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Scope("prototype")
public class UserSchoolContext {
    private final ApplicationContext applicationContext;
    private final UserService userService;

    @Autowired
    public UserSchoolContext(ApplicationContext applicationContext,
                              UserService userService) {
        this.applicationContext = applicationContext;
        this.userService = userService;
    }

    public SchoolGetModel getSchoolById(long id) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getSchoolById(id);
    }

    public SchoolGetModel getSchoolByNumber(int number) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getSchoolByNumber(number);
    }

    public List<SchoolGetModel> getSchools() {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getSchools();
    }
}
