package com.diary.diary.context;

import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.model.subject.SubjectGetModel;
import com.diary.diary.service.SubjectService;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserSubjectContext {
    private final ApplicationContext applicationContext;
    private final UserService userService;

    @Autowired
    public UserSubjectContext(ApplicationContext applicationContext,
                              UserService userService) {
        this.applicationContext = applicationContext;
        this.userService = userService;
    }

    public List<SubjectGetModel> getSubjects() {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getSubjects();
    }

    public SubjectGetModel getSubject(String name) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getSubject(name);
    }

    public SubjectGetModel getSubject(long id) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return userRole.getSubject(id);
    }
}
