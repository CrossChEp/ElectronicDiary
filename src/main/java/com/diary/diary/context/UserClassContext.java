package com.diary.diary.context;

import com.diary.diary.config.RoleNames;
import com.diary.diary.factory.RoleFactory;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.school_class.ClassGetByIdModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.model.school_class.ClassGetModel;
import com.diary.diary.model.user.UserGetModel;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service @Scope("prototype")
public class UserClassContext {
    private final UserService userService;
    private final ApplicationContext applicationContext;

    @Autowired
    public UserClassContext(UserService userService, ApplicationContext applicationContext) {
        this.userService = userService;
        this.applicationContext = applicationContext;
    }

    public List<ClassGetModel> getClasses() {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getClasses();
    }

    public ClassGetModel getSchoolClass(ClassGetByNumberModel classGetByNumberModel) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getSchoolClass(classGetByNumberModel);
    }

    public ClassGetModel getSchoolClassById(ClassGetByIdModel classGetByIdModel) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getSchoolClassById(classGetByIdModel);
    }

    public List<HomeworkGetModel> getHomework() {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getHomework();
    }

    public List<HomeworkGetModel> getHomeworkByDate(String date) throws ParseException {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getHomeworkByDate(date);
    }

    public List<HomeworkGetModel> getHomeworkBySubject(String subjectName) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getHomeworkBySubject(subjectName);
    }
}
