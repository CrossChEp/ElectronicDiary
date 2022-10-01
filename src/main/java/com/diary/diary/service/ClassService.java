package com.diary.diary.service;

import com.diary.diary.entity.ClassEntity;
import com.diary.diary.entity.SchoolEntity;
import com.diary.diary.exception.model.InvalidModelDataException;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.school_class.ClassAlreadyExists;
import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.repository.ClassRepository;
import com.diary.diary.repository.SchoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepo;
    @Autowired
    private SchoolRepository schoolRepo;

    public ClassEntity addClass(ClassAddModel classData)
            throws ClassAlreadyExists, SchoolNotFoundException, InvalidModelDataException {
        checkModelNullFields(classData);
        ClassEntity schoolClass = classRepo.findByNumberAndLetter(classData.getNumber(), classData.getLetter());
        if(schoolClass != null) {
            throw new ClassAlreadyExists("class with such data already exists");
        }
        SchoolEntity school = getSchool(classData.getSchoolNumber());
        schoolClass = createClass(classData, school);
        classRepo.save(schoolClass);
        return schoolClass;
    }

    private void checkModelNullFields(ClassAddModel model) throws InvalidModelDataException{
        if(model.getNumber() == null || model.getLetter() == null || model.getSchoolNumber() == null ||
                (model.getNumber().longValue() <=  0L || model.getNumber().longValue() > 11L)) {
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
}
