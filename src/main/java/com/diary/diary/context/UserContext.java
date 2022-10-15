package com.diary.diary.context;

import com.diary.diary.entity.RoleEntity;
import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.factory.RoleFactory;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public class UserContext {
    private UserRole userRole;


    public UserContext() throws UserNotFoundException {
        UserEntity user = new UserService().getCurrentUser();
        userRole = RoleFactory.getUserRole(user.getRole().getName());
    }

    public List<HomeworkGetModel> getHomework() throws UserNotFoundException {
        return userRole.getHomework();
    }

    public List<MarkGetModel> getMarks() throws UserNotFoundException {
        return userRole.getMarks();
    }
}
