package com.diary.diary.context;

import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.factory.RoleFactory;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Configurable @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserContext {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationContext applicationContext;

    public List<HomeworkGetModel> getHomework() throws UserNotFoundException {
        UserRole userRole = RoleFactory.getUserRole(userService.getCurrentUser().getRole().getName(), applicationContext);
        return userRole.getHomework();
    }

    public List<MarkGetModel> getMarks() throws UserNotFoundException {
        UserRole userRole = RoleFactory.getUserRole(userService.getCurrentUser().getRole().getName(), applicationContext);
        return userRole.getMarks();
    }
}
