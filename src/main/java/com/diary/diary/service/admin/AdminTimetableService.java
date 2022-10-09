package com.diary.diary.service.admin;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.TimetableEntity;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.timetable.TimetableAddModel;
import com.diary.diary.repository.TimetableRepository;
import com.diary.diary.service.TimetableService;
import com.diary.diary.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminTimetableService {

    @Autowired
    private UserService userService;
    @Autowired
    private TimetableService timetableService;

    public TimetableEntity addTimetable(TimetableAddModel timetableData)
            throws UserNotFoundException, SubjectNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        return timetableService.addTimetable(timetableData);
    }
}
