package com.diary.diary.service;

import com.diary.diary.entity.ClassEntity;
import com.diary.diary.entity.SchoolEntity;
import com.diary.diary.exception.model.InvalidModelDataException;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.school_class.ClassAlreadyExists;
import com.diary.diary.exception.school_class.ClassNotFoundException;
import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.model.school_class.ClassGetByIdModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.model.school_class.ClassGetModel;
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
}
