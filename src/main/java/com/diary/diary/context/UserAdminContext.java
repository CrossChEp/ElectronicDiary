package com.diary.diary.context;

import com.diary.diary.factory.RoleFactory;
import com.diary.diary.model.admin.AdminAddUserToClassModel;
import com.diary.diary.model.admin.AdminAddUserToSchoolModel;
import com.diary.diary.model.admin.AdminRemoveUserFromClassModel;
import com.diary.diary.model.admin.AdminRemoveUserFromSchoolModel;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.model.school.SchoolGetModel;
import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.model.school_class.ClassGetModel;
import com.diary.diary.model.subject.SubjectAddModel;
import com.diary.diary.model.subject.SubjectDeleteModel;
import com.diary.diary.model.subject.SubjectGetModel;
import com.diary.diary.model.subject.SubjectUpdateModel;
import com.diary.diary.model.timetable.TimeTableUpdateModel;
import com.diary.diary.model.timetable.TimetableAddModel;
import com.diary.diary.model.timetable.TimetableClassModel;
import com.diary.diary.model.timetable.TimetableGetModel;
import com.diary.diary.model.user.UserGetModel;
import com.diary.diary.service.UserService;
import com.diary.diary.state.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service @Scope("prototype")
public class UserAdminContext {
    private final UserService userService;
    private final ApplicationContext applicationContext;

    @Autowired
    public UserAdminContext(UserService userService, ApplicationContext applicationContext) {
        this.userService = userService;
        this.applicationContext = applicationContext;
    }

    public ClassGetModel addClass(ClassAddModel classData) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return ClassGetModel.toModel(userRole.addClass(classData));
    }

    public ClassGetModel addUserToClass(AdminAddUserToClassModel adminAddUserToClassModel) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return ClassGetModel.toModel(userRole.addUserToClass(adminAddUserToClassModel));
    }

    public ClassGetModel deleteClass(long id) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return ClassGetModel.toModel(userRole.deleteClass(id));
    }

    public UserGetModel removeUserFromClass(AdminRemoveUserFromClassModel adminRemoveUserFromClassModel) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return UserGetModel.toModel(userRole.removeUserFromClass(adminRemoveUserFromClassModel));
    }

    public UserGetModel addUserToSchool(AdminAddUserToSchoolModel userSchoolModel) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return UserGetModel.toModel(userRole.addUserToSchool(userSchoolModel));
    }

    public SchoolGetModel addSchool(SchoolAddModel schoolData) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return SchoolGetModel.toModel(userRole.addSchool(schoolData));
    }

    public SchoolGetModel deleteSchool(long schoolId) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return SchoolGetModel.toModel(userRole.deleteSchool(schoolId));
    }

    public UserGetModel removeUserFromSchool(AdminRemoveUserFromSchoolModel removeUserFromSchoolModel) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return UserGetModel.toModel(userRole.removeUserFromSchool(removeUserFromSchoolModel));
    }

    public SubjectGetModel addSubject(SubjectAddModel subjectData) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return SubjectGetModel.toModel(userRole.addSubject(subjectData));
    }

    public SubjectGetModel updateSubject(SubjectUpdateModel newSubjectData) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return SubjectUpdateModel.toModel(userRole.updateSubject(newSubjectData));
    }

    public SubjectGetModel deleteSubject(SubjectDeleteModel subjectDeleteData) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return SubjectGetModel.toModel(userRole.deleteSubject(subjectDeleteData));
    }

    public TimetableGetModel addTimetable(TimetableAddModel timetable) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return TimetableGetModel.toModel(userRole.addTimetable(timetable));
    }

    public TimetableGetModel updateTimetable(TimeTableUpdateModel newTimetable) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return TimetableGetModel.toModel(userRole.updateTimetable(newTimetable));
    }

    public TimetableGetModel deleteTimetable(long timetableId) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return TimetableGetModel.toModel(userRole.deleteTimetable(timetableId));
    }

    public ClassGetModel addTimetableToClass(TimetableClassModel timetableAndClass) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return ClassGetModel.toModel(userRole.addTimetableToClass(timetableAndClass));
    }

    public ClassGetModel deleteTimetableFromClass(long classId) {
        UserRole userRole = ContextSystemMethods.getStateRoleOfUser(userService, applicationContext);
        return ClassGetModel.toModel(userRole.deleteTimetableFromClass(classId));
    }
}
