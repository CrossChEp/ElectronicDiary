package com.diary.diary.service;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.*;
import com.diary.diary.exception.model.InvalidModelDataException;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.school_class.ClassAlreadyExists;
import com.diary.diary.exception.school_class.ClassNotFoundException;
import com.diary.diary.exception.timetable.TimetableAlreadyExists;
import com.diary.diary.exception.timetable.TimetableNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.model.school_class.ClassGetByIdModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.model.school_class.ClassGetModel;
import com.diary.diary.model.timetable.TimetableClassModel;
import com.diary.diary.repository.ClassRepository;
import com.diary.diary.repository.SchoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepo;
    @Autowired
    private SchoolRepository schoolRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private TimetableService timetableService;

    public ClassEntity getClassEntity(long classId) throws ClassNotFoundException {
        return classRepo.findById(classId)
                .orElseThrow(() -> new ClassNotFoundException("class with such id doesn't exists"));
    }

    public ClassEntity addClass(ClassAddModel classData)
            throws ClassAlreadyExists, SchoolNotFoundException, InvalidModelDataException {
        checkModelValidation(classData);
        ClassEntity schoolClass = classRepo.findByNumberAndLetter(classData.getNumber(), classData.getLetter());
        if(schoolClass != null) {
            throw new ClassAlreadyExists("class with such data already exists");
        }
        SchoolEntity school = getSchool(classData.getSchoolNumber());
        schoolClass = createClass(classData, school);
        classRepo.save(schoolClass);
        return schoolClass;
    }

    private void checkModelValidation(ClassAddModel model) throws InvalidModelDataException{
        if(model.getNumber() == null || model.getLetter() == null || model.getSchoolNumber() == null ||
                (model.getNumber().longValue() <=  0L || model.getNumber().longValue() >= 11L)) {
            throw new InvalidModelDataException("model is invalid");
        }
    }

    private SchoolEntity getSchool(int number) throws SchoolNotFoundException {
        SchoolEntity school = schoolRepo.findByNumber(number);
        if(school == null) {
            throw new SchoolNotFoundException("school " + number + " doesn't exists");
        }
        return school;
    }

    private ClassEntity createClass(ClassAddModel classData, SchoolEntity school) {
        ClassEntity schoolClass = new ClassEntity();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(classData, schoolClass);
        schoolClass.setSchool(school);
        return schoolClass;
    }

    public List<ClassGetModel> getClasses() {
        List<ClassEntity> classes = classRepo.findAll();
        return createModelList(classes);
    }

    public ClassGetModel getSchoolClass(ClassGetByNumberModel classData)
            throws SchoolNotFoundException, ClassNotFoundException {

        SchoolEntity school = schoolRepo.findByNumber(classData.getSchoolNumber());
        if(school == null) {
            throw new SchoolNotFoundException("school with such number doesn't exists");
        }
        ClassEntity schoolClass = findClassInSchool(classData, school);
        if(schoolClass == null) {
            throw new ClassNotFoundException("class with such parameters not found");
        }
        return ClassGetModel.toModel(schoolClass);
    }

    private ClassEntity findClassInSchool(ClassGetByNumberModel classData, SchoolEntity school) {
        for(var schoolClass: school.getClasses()) {
            if(schoolClass.getNumber() == classData.getNumber()
                    && schoolClass.getLetter() == classData.getLetter()) {
                return schoolClass;
            }
        }
        return null;
    }

    public ClassGetModel getClassBySchoolId(ClassGetByIdModel classData)
            throws SchoolNotFoundException, ClassNotFoundException {
        SchoolEntity school = schoolRepo.findById(classData.getSchoolId())
                .orElseThrow(() -> new SchoolNotFoundException("school with such id doesn't exists"));
        ClassEntity schoolClass = findClassInSchoolById(classData, school);
        if(schoolClass == null) {
            throw new ClassNotFoundException("class with such id doesn't exists");
        }
        return ClassGetModel.toModel(schoolClass);
    }

    private ClassEntity findClassInSchoolById(ClassGetByIdModel classData, SchoolEntity school) {
        for(var schoolClass: school.getClasses()) {
            if(schoolClass.getNumber() == classData.getNumber()
                && schoolClass.getLetter() == classData.getLetter()) {
                return schoolClass;
            }
        }
        return null;
    }

    private List<ClassGetModel> createModelList(List<ClassEntity> classes) {
        return classes.stream().map(ClassGetModel::toModel).toList();
    }

    public ClassEntity deleteClass(long id) throws ClassNotFoundException, UserNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        ClassEntity schoolClass = getClassEntity(id);
        classRepo.delete(schoolClass);
        return schoolClass;
    }

    public ClassEntity addTimetableToClass(TimetableClassModel timetableClass)
            throws TimetableNotFoundException, ClassNotFoundException, TimetableAlreadyExists {
        ClassEntity schoolClass = getClassEntity(timetableClass.getClassId());
        if(schoolClass.getTimetable() != null) {
            throw new TimetableAlreadyExists("this class already has timetable");
        }
        TimetableEntity timetable = timetableService.getTimetable(timetableClass.getTimetableId());
        schoolClass.setTimetable(timetable);
        classRepo.save(schoolClass);
        return schoolClass;
    }

    public ClassEntity deleteTimetableFromClass(long clasId)
            throws ClassNotFoundException, TimetableNotFoundException {
        ClassEntity schoolClass = getClassEntity(clasId);
        if(schoolClass.getTimetable() == null) {
            throw new TimetableNotFoundException("this class doesn't have timetable");
        }
        schoolClass.setTimetable(null);
        classRepo.save(schoolClass);
        return schoolClass;
    }

    public List<HomeworkGetModel> getHomework() throws UserNotFoundException {
        UserEntity student = userService.getCurrentUser();
        List<HomeworkEntity> homeworks = student.getUserClass().getHomework();
        return convertToHomeworkGetModel(homeworks);
    }

    private List<HomeworkGetModel> convertToHomeworkGetModel(List<HomeworkEntity> homeworks) {
        return homeworks.stream().map(HomeworkGetModel::toModel).toList();
    }
}
