package com.diary.diary.state;

import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.school_class.ClassNotFoundException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.mark.DateAndSubjectModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.model.school.SchoolGetModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.model.school_class.ClassGetModel;
import com.diary.diary.model.subject.SubjectGetModel;
import com.diary.diary.service.*;

import java.text.ParseException;
import java.util.List;

public class StudentRole extends DefaultRole {

    private final MarkService markService = new MarkService();
    private final SubjectService subjectService = new SubjectService();
    private final SchoolService schoolService = new SchoolService();
    private final ClassService classService = new ClassService();
    private final HomeworkService homeworkService = new HomeworkService();
    private final UserService userService = new UserService();

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
    public List<MarkGetModel> getMarksByDateAndSubject(DateAndSubjectModel dateAndSubject)
            throws UserNotFoundException, ParseException {
        return userService.getMarksByDateAndSubject     (dateAndSubject);
    }

    @Override
    public SubjectGetModel getSubject(long id) throws SubjectNotFoundException {
        return  subjectService.getSubject(id);
    }

    @Override
    public SubjectGetModel getSubject(String name) throws SubjectNotFoundException {
        return subjectService.getSubject(name);
    }

    @Override
    public SchoolGetModel getSchoolById(long schoolId) throws SchoolNotFoundException {
        return schoolService.getSchoolById(schoolId);
    }

    @Override
    public SchoolGetModel getSchoolByNumber(int schoolNumber) throws SchoolNotFoundException {
        return schoolService.getSchoolByNumber(schoolNumber);
    }

    @Override
    public List<SchoolGetModel> getSchools() {
        return schoolService.getSchools();
    }

    @Override
    public ClassGetModel getSchoolClass(ClassGetByNumberModel classData)
            throws SchoolNotFoundException, ClassNotFoundException{
        return classService.getSchoolClass(classData);
    }

    @Override
    public List<ClassGetModel> getClasses() {
        return classService.getClasses();
    }

    @Override
    public List<HomeworkGetModel> getHomework() throws UserNotFoundException {
        return classService.getHomework();
    }

    @Override
    public List<HomeworkGetModel> getHomeworkByDate(String date)
            throws UserNotFoundException, ParseException {
        return classService.getHomeworkByDate(date);
    }

    @Override
    public List<HomeworkGetModel> getHomeworkBySubject(String subjectName) {
        return super.getHomeworkBySubject(subjectName);
    }
}
