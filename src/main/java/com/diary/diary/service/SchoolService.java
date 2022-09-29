package com.diary.diary.service;

import com.diary.diary.entity.SchoolEntity;
import com.diary.diary.exception.school.SchoolAlreadyExistsException;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.repository.SchoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepo;

    public SchoolEntity addSchool(SchoolAddModel schoolData) throws SchoolAlreadyExistsException {
        SchoolEntity school = schoolRepo.findByNumber(schoolData.getNumber());
        if(school != null) {
            throw new SchoolAlreadyExistsException("school with such a number already exists");
        }
        school = new SchoolEntity();
        school.setNumber(schoolData.getNumber());
        schoolRepo.save(school);
        return school;
    }
}
