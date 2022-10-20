package com.diary.diary.state;

import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.school_class.ClassNotFoundException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.mark.DateAndSubjectModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.model.school.SchoolGetModel;
import com.diary.diary.model.school_class.ClassGetByIdModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.model.school_class.ClassGetModel;
import com.diary.diary.model.subject.SubjectGetModel;
import com.diary.diary.service.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

public class StudentRole extends DefaultRole {
    private SubjectService subjectService;
    private SchoolService schoolService;
    private ClassService classService;
    private UserService userService;

    public StudentRole() {
    }

    public StudentRole(ApplicationContext applicationContext) {
        super(applicationContext);
        this.userService = new UserService(applicationContext);
        this.subjectService = new SubjectService(applicationContext);
        this.schoolService = new SchoolService(applicationContext);
        this.classService = new ClassService(applicationContext);
    }

    @Override
    public List<MarkGetModel> getMarks() {
        return userService.getMarks();
    }

    @Override
    public List<MarkGetModel> getMarksByDate(String date) throws ParseException {
        return userService.getMarksByDate(date);
    }

    @Override
    public List<MarkGetModel> getMarksBySubject(String subjectName) {
        return userService.getMarksBySubject(subjectName);
    }

    @Override
    public List<MarkGetModel> getMarksByDateAndSubject(DateAndSubjectModel dateAndSubject)
            throws ParseException {
        return userService.getMarksByDateAndSubject(dateAndSubject);
    }

    @Override
    public List<SubjectGetModel> getSubjects() {
        return subjectService.getSubjects();
    }

    @Override
    public SubjectGetModel getSubject(long id) {
        return  subjectService.getSubject(id);
    }

    @Override
    public SubjectGetModel getSubject(String name) {
        return subjectService.getSubject(name);
    }

    @Override
    public SchoolGetModel getSchoolById(long schoolId) {
        return schoolService.getSchoolById(schoolId);
    }

    @Override
    public SchoolGetModel getSchoolByNumber(int schoolNumber) {
        return schoolService.getSchoolByNumber(schoolNumber);
    }

    @Override
    public List<SchoolGetModel> getSchools() {
        return schoolService.getSchools();
    }

    @Override
    public ClassGetModel getSchoolClass(ClassGetByNumberModel classData) {
        return classService.getSchoolClass(classData);
    }

    @Override
    public ClassGetModel getSchoolClassById(ClassGetByIdModel classGetByIdModel) {
        return classService.getClassBySchoolId(classGetByIdModel);
    }

    @Override
    public List<ClassGetModel> getClasses() {
        return classService.getClasses();
    }

    @Override
    public List<HomeworkGetModel> getHomework() {
        return classService.getHomework();
    }

    @Override
    public List<HomeworkGetModel> getHomeworkByDate(String date) throws ParseException {
        return classService.getHomeworkByDate(date);
    }

    @Override
    public List<HomeworkGetModel> getHomeworkBySubject(String subjectName) {
        return classService.getHomeworkBySubject(subjectName);
    }
}
