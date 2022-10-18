package com.diary.diary.context;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.user.UserAlreadyExistsException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.factory.RoleFactory;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.mark.DateAndSubjectModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.model.user.UserAddModel;
import com.diary.diary.model.user.UserGetModel;
import com.diary.diary.model.user.UserUpdateModel;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service @Configurable @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserContext {


    private final UserService userService;
    private final ApplicationContext applicationContext;

    @Autowired
    public UserContext(UserService userService, ApplicationContext applicationContext) {
        this.userService = userService;
        this.applicationContext = applicationContext;
    }

    public UserGetModel register(UserAddModel userData) throws UserAlreadyExistsException {
        UserRole userRole = RoleFactory.getUserRole(RoleNames.UNREGISTERED, applicationContext);
        return userRole.register(userData);
    }

    public UserGetModel getUser(long userId) {
        UserRole userRole = getStateRoleOfUser();
        return userRole.getUser(userId);
    }

    public List<UserGetModel> getUsers() {
        UserRole userRole = getStateRoleOfUser();
        return userRole.getAllUsers();
    }

    public UserGetModel updateUser(UserUpdateModel newUserData) {
        UserRole userRole = getStateRoleOfUser();
        return userRole.updateUser(newUserData);
    }

    public UserGetModel deleteUser() {
        UserRole userRole = getStateRoleOfUser();
        return userRole.deleteUser();
    }

    public List<MarkGetModel> getMarksByDate(String date) throws ParseException {
        UserRole userRole = getStateRoleOfUser();
        return userRole.getMarksByDate(date);
    }

    public List<MarkGetModel> getMarksByDateAndSubject(DateAndSubjectModel dateAndSubjectModel)
            throws ParseException {
        UserRole userRole = getStateRoleOfUser();
        return userRole.getMarksByDateAndSubject(dateAndSubjectModel);
    }

    public List<MarkGetModel> getMarksBySubject(String subject) {
        UserRole userRole = getStateRoleOfUser();
        return userRole.getMarksBySubject(subject);
    }

    public List<HomeworkGetModel> getHomework() throws UserNotFoundException {
        UserRole userRole = RoleFactory.getUserRole(userService.getCurrentUser().getRole().getName(), applicationContext);
        return userRole.getHomework();
    }

    public List<MarkGetModel> getMarks() throws UserNotFoundException {
        UserRole userRole = RoleFactory.getUserRole(userService.getCurrentUser().getRole().getName(), applicationContext);
        return userRole.getMarks();
    }

    private UserRole getStateRoleOfUser() {
        return RoleFactory.getUserRole(userService.getCurrentUser().getRole().getName(),
                applicationContext);
    }
}
