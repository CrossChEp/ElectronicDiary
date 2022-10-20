package com.diary.diary.state;

import com.diary.diary.entity.*;
import com.diary.diary.model.admin.AdminAddUserToClassModel;
import com.diary.diary.model.admin.AdminAddUserToSchoolModel;
import com.diary.diary.model.admin.AdminRemoveUserFromClassModel;
import com.diary.diary.model.admin.AdminRemoveUserFromSchoolModel;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.model.subject.SubjectAddModel;
import com.diary.diary.model.subject.SubjectDeleteModel;
import com.diary.diary.model.subject.SubjectUpdateModel;
import com.diary.diary.model.timetable.TimeTableUpdateModel;
import com.diary.diary.model.timetable.TimetableAddModel;
import com.diary.diary.model.timetable.TimetableClassModel;
import com.diary.diary.service.ClassService;
import com.diary.diary.service.SchoolService;
import com.diary.diary.service.admin.AdminService;
import com.diary.diary.service.admin.AdminSubjectService;
import com.diary.diary.service.admin.AdminTimetableService;
import org.springframework.context.ApplicationContext;

public class AdminRole extends TeacherRole {

    private final AdminService adminService;
    private final ClassService classService;
    private final AdminSubjectService adminSubjectService;
    private final AdminTimetableService adminTimetableService;

    private final SchoolService schoolService;

    public AdminRole(ApplicationContext applicationContext) {
        super(applicationContext);
        this.adminService = new AdminService(applicationContext);
        this.classService = new ClassService(applicationContext);
        this.adminSubjectService = new AdminSubjectService(applicationContext);
        this.adminTimetableService = new AdminTimetableService(applicationContext);
        this.schoolService = new SchoolService(applicationContext);
    }

    @Override
    public ClassEntity addClass(ClassAddModel classData) {
        return classService.addClass(classData);
    }

    @Override
    public ClassEntity addUserToClass(AdminAddUserToClassModel userAndClassModel) {
        return adminService.addUserToClass(userAndClassModel);
    }

    @Override
    public ClassEntity deleteClass(long id) {
        return classService.deleteClass(id);
    }

    @Override
    public UserEntity removeUserFromClass(AdminRemoveUserFromClassModel userClassModel) {
        return adminService.removeUserFromClass(userClassModel);
    }

    @Override
    public UserEntity addUserToSchool(AdminAddUserToSchoolModel userAndSchool) {
        return adminService.addUserToSchool(userAndSchool);
    }

    @Override
    public SchoolEntity addSchool(SchoolAddModel schoolData) {
        return schoolService.addSchool(schoolData);
    }

    @Override
    public SchoolEntity deleteSchool(long id) {
        return schoolService.deleteSchool(id);
    }

    @Override
    public UserEntity removeUserFromSchool(AdminRemoveUserFromSchoolModel userSchoolModel) {
        return adminService.removeUserFromSchool(userSchoolModel);
    }

    @Override
    public SubjectEntity addSubject(SubjectAddModel subjectData) {
        return adminSubjectService.addSubject(subjectData);
    }

    @Override
    public SubjectEntity updateSubject(SubjectUpdateModel newSubjectModel) {
        return adminSubjectService.updateSubject(newSubjectModel);
    }

    @Override
    public SubjectEntity deleteSubject(SubjectDeleteModel subjectDeleteData) {
        return adminSubjectService.deleteSubject(subjectDeleteData);
    }

    @Override
    public TimetableEntity addTimetable(TimetableAddModel timetableData) {
        return adminTimetableService.addTimetable(timetableData);
    }

    @Override
    public TimetableEntity updateTimetable(TimeTableUpdateModel newTimetableData) {
        return adminTimetableService.updateTimetable(newTimetableData);
    }

    @Override
    public TimetableEntity deleteTimetable(long id) {
        return adminTimetableService.deleteTimetable(id);
    }

    @Override
    public ClassEntity addTimetableToClass(TimetableClassModel timetableClass) {
        return adminTimetableService.addTimetableToClass(timetableClass);
    }

    @Override
    public ClassEntity deleteTimetableFromClass(long classId) {
        return adminTimetableService.deleteTimetableFromClass(classId);
    }
}
