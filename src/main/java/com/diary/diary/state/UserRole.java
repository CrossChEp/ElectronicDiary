package com.diary.diary.state;

import com.diary.diary.entity.*;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserAlreadyExistsException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.admin.AdminAddUserToClassModel;
import com.diary.diary.model.admin.AdminRemoveUserFromClassModel;
import com.diary.diary.model.admin.AdminRemoveUserFromSchoolModel;
import com.diary.diary.model.homework.HomeworkAddModel;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.homework.HomeworkUpdateModel;
import com.diary.diary.model.mark.DateAndSubjectModel;
import com.diary.diary.model.mark.MarkAddModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.model.school.SchoolGetModel;
import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.model.school_class.ClassGetByIdModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.model.school_class.ClassGetModel;
import com.diary.diary.model.subject.SubjectAddModel;
import com.diary.diary.model.subject.SubjectDeleteModel;
import com.diary.diary.model.subject.SubjectGetModel;
import com.diary.diary.model.subject.SubjectUpdateModel;
import com.diary.diary.model.timetable.TimeTableUpdateModel;
import com.diary.diary.model.timetable.TimetableAddModel;
import com.diary.diary.model.timetable.TimetableClassModel;
import com.diary.diary.model.user.UserAddModel;
import com.diary.diary.model.user.UserGetModel;
import com.diary.diary.model.user.UserUpdateModel;
import org.apache.commons.lang3.NotImplementedException;

import java.text.ParseException;
import java.util.List;

public interface UserRole {
    default UserGetModel getUser(long id) throws UserNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default UserGetModel register(UserAddModel userData) throws UserAlreadyExistsException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<UserGetModel> getAllUsers() {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default UserGetModel updateUser(UserUpdateModel newUserData) throws UserNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default UserGetModel deleteUser() throws UserNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<MarkGetModel> getMarks() throws UserNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<MarkGetModel> getMarksByDate(String date) throws UserNotFoundException, ParseException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<MarkGetModel> getMarksBySubject(String subjectName) throws UserNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<MarkGetModel> getMarksByDateAndSubject(DateAndSubjectModel dateAndSubject) throws UserNotFoundException, ParseException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<SubjectGetModel> getSubjects() {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default SubjectGetModel getSubject(long id) throws SubjectNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default SubjectGetModel getSubject(String name) throws SubjectNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default SchoolGetModel getSchoolById(long schoolId) throws SchoolNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default SchoolGetModel getSchoolByNumber(int schoolNumber) throws SchoolNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<SchoolGetModel> getSchools() {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default ClassGetModel getSchoolClass(ClassGetByNumberModel classData) throws com.diary.diary.exception.school_class.ClassNotFoundException, SchoolNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<ClassGetModel> getClasses() {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default ClassGetModel getSchoolClassById(ClassGetByIdModel classGetByIdModel) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default HomeworkGetModel addHomework(HomeworkAddModel homeworkData) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default HomeworkGetModel updateHomework(long homeworkId, HomeworkUpdateModel homeworkNewData) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default HomeworkGetModel deleteHomework(long id) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<HomeworkGetModel> getHomework() throws UserNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<HomeworkGetModel> getHomeworkByDate(String date) throws UserNotFoundException, ParseException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default List<HomeworkGetModel> getHomeworkBySubject(String subjectName) throws SubjectNotFoundException {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default MarkGetModel addMark(MarkAddModel markData) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default MarkGetModel removeMark(long id) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default MarkGetModel updateMark(long markId, int mark) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default ClassEntity addClass(ClassAddModel classData) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default ClassEntity addUserToClass(AdminAddUserToClassModel userAndClassModel) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default ClassEntity deleteClass(long id) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default UserEntity removeUserFromClass(AdminRemoveUserFromClassModel userClassModel) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default SchoolEntity addSchool(SchoolAddModel schoolData) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default SchoolEntity deleteSchool(long id) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default UserEntity removeUserFromSchool(AdminRemoveUserFromSchoolModel userSchoolModel) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default SubjectEntity addSubject(SubjectAddModel subjectData) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default SubjectEntity updateSubject(SubjectUpdateModel newSubjectModel) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default SubjectEntity deleteSubject(SubjectDeleteModel subjectDeleteData) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default TimetableEntity addTimetable(TimetableAddModel timetableData) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default TimetableEntity updateTimetable(TimeTableUpdateModel newTimetableData) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default TimetableEntity deleteTimetable(long id) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default ClassEntity addTimetableToClass(TimetableClassModel timetableClass) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }

    default ClassEntity deleteTimetableFromClass(long classId) {
        throw new NotImplementedException("user have no rights to use this functionality");
    }
}
