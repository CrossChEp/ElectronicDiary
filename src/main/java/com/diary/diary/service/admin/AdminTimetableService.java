package com.diary.diary.service.admin;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.ClassEntity;
import com.diary.diary.entity.TimetableEntity;
import com.diary.diary.exception.school_class.ClassNotFoundException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.timetable.TimetableAlreadyExists;
import com.diary.diary.exception.timetable.TimetableNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.timetable.TimeTableUpdateModel;
import com.diary.diary.model.timetable.TimetableAddModel;
import com.diary.diary.model.timetable.TimetableClassModel;
import com.diary.diary.repository.TimetableRepository;
import com.diary.diary.service.ClassService;
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

    @Autowired
    private ClassService classService;

    public TimetableEntity addTimetable(TimetableAddModel timetableData)
            throws UserNotFoundException, SubjectNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        return timetableService.addTimetable(timetableData);
    }

    public TimetableEntity updateTimetable(TimeTableUpdateModel newTimetableData)
        throws UserNotFoundException, TimetableNotFoundException, SubjectNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        return timetableService.updateTimetable(newTimetableData);
    }

    public TimetableEntity deleteTimetable(long id)
            throws UserNotFoundException, TimetableNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        return timetableService.deleteTimetable(id);
    }

    public ClassEntity addTimetableToClass(TimetableClassModel timetableClass)
            throws UserNotFoundException, TimetableNotFoundException,
            ClassNotFoundException, TimetableAlreadyExists {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        return classService.addTimetableToClass(timetableClass);
    }

    public ClassEntity deleteTimetableFromClass(long classId)
            throws UserNotFoundException, TimetableNotFoundException, ClassNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        return classService.deleteTimetableFromClass(classId);
    }
}
