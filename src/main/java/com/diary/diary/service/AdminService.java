package com.diary.diary.service;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.SchoolEntity;
import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.admin.AdminAddUserToSchoolModel;
import com.diary.diary.repository.SchoolRepository;
import com.diary.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public UserEntity addUserToSchool(AdminAddUserToSchoolModel addUserToSchoolData)
            throws UserNotFoundException, SchoolNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        UserEntity user = userService.getUserEntity(addUserToSchoolData.getUserId());
        SchoolEntity school = schoolService.getSchoolEntityByNumber(addUserToSchoolData.getSchoolNumber());
//        user.setSchool(school);
        school.getStudents().add(user);
        schoolRepo.save(school);
        userRepo.save(user);
        return user;
    }
}
