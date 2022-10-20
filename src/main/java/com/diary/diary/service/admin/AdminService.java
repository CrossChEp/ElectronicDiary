package com.diary.diary.service.admin;

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
import com.diary.diary.model.admin.AdminRemoveUserFromClassModel;
import com.diary.diary.model.admin.AdminRemoveUserFromSchoolModel;
import com.diary.diary.repository.ClassRepository;
import com.diary.diary.repository.SchoolRepository;
import com.diary.diary.repository.UserRepository;
import com.diary.diary.service.ClassService;
import com.diary.diary.service.SchoolService;
import com.diary.diary.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private UserService userService;

    private UserRepository userRepo;

    private SchoolRepository schoolRepo;

    private SchoolService schoolService;

    private ClassService classService;
    private ClassRepository classRepo;

    public AdminService() {
    }

    public AdminService(ApplicationContext applicationContext) {
        this.userService = new UserService(applicationContext);
        this.userRepo = applicationContext.getBean(UserRepository.class);
        this.schoolRepo = applicationContext.getBean(SchoolRepository.class);
        this.schoolService = new SchoolService(applicationContext);
        this.classService = new ClassService(applicationContext);
        this.classRepo = applicationContext.getBean(ClassRepository.class);
    }

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

    public UserEntity removeUserFromClass(AdminRemoveUserFromClassModel userClassModel)
            throws UserNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        UserEntity student = userService.getUserEntity(userClassModel.getUserId());
        if(student.getUserClass() == null) {
            throw new UserNotFoundException("there is no such user in class");
        }
        student.setUserClass(null);
        userRepo.save(student);
        return student;
    }

    public UserEntity removeUserFromSchool(AdminRemoveUserFromSchoolModel userSchoolModel)
            throws UserNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        UserEntity student = userService.getUserEntity(userSchoolModel.getUserId());
        if(student.getSchool() == null) {
            throw new UserNotFoundException("there is no such user in school");
        }
        student.setSchool(null);
        userRepo.save(student);
        return student;
    }
}
