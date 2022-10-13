package com.diary.diary.state;

import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.mark.DateAndSubjectModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.model.school.SchoolGetModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.model.school_class.ClassGetModel;
import com.diary.diary.model.subject.SubjectGetModel;
import com.diary.diary.service.MarkService;
import com.diary.diary.service.UserService;

import java.text.ParseException;
import java.util.List;

public class StudentRole extends DefaultRole {

    private final UserService userService = new UserService();
    private final MarkService markService = new MarkService();

    @Override
    public List<MarkGetModel> getMarks() throws UserNotFoundException {
        return userService.getMarks();
    }

    @Override
    public List<MarkGetModel> getMarksByDate(String date) throws UserNotFoundException, ParseException {
        return userService.getMarksByDate(date);
    }

    @Override
    public List<MarkGetModel> getMarksBySubject(String subjectName) throws UserNotFoundException {
        return userService.getMarksBySubject(subjectName);
    }

    @Override
    public List<MarkGetModel> getMarksByDateAndSubject(DateAndSubjectModel dateAndSubject) {
        return super.getMarksByDateAndSubject(dateAndSubject);
    }

    @Override
    public SubjectGetModel getSubject(long id) {
        return super.getSubject(id);
    }

    @Override
    public SubjectGetModel getSubject(String name) {
        return super.getSubject(name);
    }

    @Override
    public SchoolGetModel getSchoolById(long schoolId) {
        return super.getSchoolById(schoolId);
    }

    @Override
    public SchoolGetModel getSchoolByNumber(int schoolNumber) {
        return super.getSchoolByNumber(schoolNumber);
    }

    @Override
    public List<SchoolGetModel> getSchools() {
        return super.getSchools();
    }

    @Override
    public ClassGetModel getSchoolClass(ClassGetByNumberModel classData) {
        return super.getSchoolClass(classData);
    }

    @Override
    public List<ClassGetModel> getClasses() {
        return super.getClasses();
    }

    @Override
    public List<HomeworkGetModel> getHomework() {
        return super.getHomework();
    }

    @Override
    public List<HomeworkGetModel> getHomeworkByDate(String date) {
        return super.getHomeworkByDate(date);
    }

    @Override
    public List<HomeworkGetModel> getHomeworkBySubject(String subjectName) {
        return super.getHomeworkBySubject(subjectName);
    }
}
