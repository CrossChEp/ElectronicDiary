package com.diary.diary.service;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.ClassEntity;
import com.diary.diary.entity.SchoolEntity;
import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.school_class.ClassNotFoundException;
import com.diary.diary.exception.user.UserAlreadyExistsException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.admin.AdminAddUserToClassModel;
import com.diary.diary.model.admin.AdminAddUserToSchoolModel;
import com.diary.diary.repository.ClassRepository;
import com.diary.diary.repository.SchoolRepository;
import com.diary.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SchoolRepository schoolRepo;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private ClassService classService;
    @Autowired
    private ClassRepository classRepo;

    public UserEntity addUserToSchool(AdminAddUserToSchoolModel addUserToSchoolData)
            throws UserNotFoundException, SchoolNotFoundException, UserAlreadyExistsException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        UserEntity user = userService.getUserEntity(addUserToSchoolData.getUserId());
        SchoolEntity school = schoolService.getSchoolEntityByNumber(addUserToSchoolData.getSchoolNumber());
        if(school.getStudents().contains(user)) {
            throw new UserAlreadyExistsException("user already in this school");
        }
        user.setSchool(school);
        schoolRepo.save(school);
        userRepo.save(user);
        return user;
    }


    public ClassEntity addUserToClass(AdminAddUserToClassModel userAndClassModel)
            throws UserNotFoundException, ClassNotFoundException, UserAlreadyExistsException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        UserEntity user = userService.getUserEntity(userAndClassModel.getUserId());
        ClassEntity schoolClass = classService.getClassEntity(userAndClassModel.getClassId());
        if(!isUserCanBeAddedToThisClass(schoolClass, user)) {
            throw new UserNotFoundException("user has no available school to be added to this class");
        }
        if(schoolClass.getStudents().contains(user)) {
            throw new UserAlreadyExistsException("user already in this class");
        }
        user.setUserClass(schoolClass);
        classRepo.save(schoolClass);
        userRepo.save(user);
        return schoolClass;
    }

    private boolean isUserCanBeAddedToThisClass(ClassEntity schoolClass, UserEntity user) {
        if(user.getUserClass() != null || user.getSchool() == null) {
            return false;
        }
        return user.getSchool().getClasses().contains(schoolClass);
    }
}
