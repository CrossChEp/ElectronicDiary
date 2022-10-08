package com.diary.diary.service.admin;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.SubjectEntity;
import com.diary.diary.exception.model.InvalidModelDataException;
import com.diary.diary.exception.subject.SubjectAlreadyExistsException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.subject.SubjectAddModel;
import com.diary.diary.model.subject.SubjectDeleteModel;
import com.diary.diary.model.subject.SubjectUpdateModel;
import com.diary.diary.service.SubjectService;
import com.diary.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class AdminSubjectService {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    public SubjectEntity addSubject(SubjectAddModel subjectData)
            throws UserNotFoundException, SubjectAlreadyExistsException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        return subjectService.addSubject(subjectData);
    }

    public SubjectEntity updateSubject(SubjectUpdateModel newSubjectModel)
            throws UserNotFoundException, SubjectNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        return subjectService.updateSubject(newSubjectModel);
    }

    public SubjectEntity deleteSubject(SubjectDeleteModel subjectDeleteData)
            throws UserNotFoundException, SubjectNotFoundException, InvalidModelDataException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        return subjectService.deleteSubject(subjectDeleteData);
    }
}
