package com.diary.diary.context;

import com.diary.diary.model.homework.HomeworkAddModel;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.homework.HomeworkUpdateModel;
import com.diary.diary.model.mark.MarkAddModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service @Scope("prototype")
public class TeacherContext {
    private final UserService userService;
    private final ApplicationContext applicationContext;

    @Autowired
    public TeacherContext(UserService userService, ApplicationContext applicationContext) {
        this.userService = userService;
        this.applicationContext = applicationContext;
    }

    public MarkGetModel addMark(MarkAddModel markData) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.addMark(markData);
    }

    public MarkGetModel removeMark(long id) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.removeMark(id);
    }

    public MarkGetModel updateMark(long markId, int newMark) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.updateMark(markId, newMark);
    }

    public HomeworkGetModel addHomework(HomeworkAddModel homeworkData) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.addHomework(homeworkData);
    }

    public HomeworkGetModel updateHomework(long id, HomeworkUpdateModel newHomeworkData) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.updateHomework(id, newHomeworkData);
    }

    public HomeworkGetModel deleteHomework(long homeworkId) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.deleteHomework(homeworkId);
    }
}
