package com.diary.diary.service;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.SchoolEntity;
import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.school.SchoolAlreadyExistsException;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.model.school.SchoolGetModel;
import com.diary.diary.repository.SchoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepo;
    @Autowired
    private UserService userService;

    public SchoolEntity addSchool(SchoolAddModel schoolData)
            throws SchoolAlreadyExistsException, UserNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
        SchoolEntity school = schoolRepo.findByNumber(schoolData.getNumber());
        if(school != null) {
            throw new SchoolAlreadyExistsException("school with such a number already exists");
        }
        school = new SchoolEntity();
        school.setNumber(schoolData.getNumber());
        schoolRepo.save(school);
        return school;
    }

    public SchoolGetModel getSchoolById(long schoolId) throws SchoolNotFoundException {
        SchoolEntity school =  schoolRepo.findById(schoolId)
                .orElseThrow(() -> new SchoolNotFoundException("school with such id doesn't exists"));
        return SchoolGetModel.toModel(school);
    }

    public SchoolGetModel getSchoolByNumber(int schoolNumber) throws SchoolNotFoundException {
        SchoolEntity school = schoolRepo.findByNumber(schoolNumber);
        if(school == null) {
            throw new SchoolNotFoundException("school with such number doesn't exists");
        }
        return SchoolGetModel.toModel(school);
    }

    public List<SchoolGetModel> getSchools() {
        List<SchoolEntity> schools = schoolRepo.findAll();
        return generateGetModelList(schools);
    }

    public List<SchoolGetModel> generateGetModelList(List<SchoolEntity> schools) {
        return schools.stream().map(SchoolGetModel::toModel).toList();
    }

    public SchoolEntity getSchoolEntity(long schoolId) throws SchoolNotFoundException {
        return schoolRepo.findById(schoolId)
                .orElseThrow(() -> new SchoolNotFoundException("school with id " + schoolId + " not found"));
    }

    public SchoolEntity getSchoolEntityByNumber(int schoolNumber) throws SchoolNotFoundException {
        SchoolEntity school =  schoolRepo.findByNumber(schoolNumber);
        if(school == null) {
            throw new SchoolNotFoundException("school with number " + schoolNumber + " not found");
        }
        return school;
    }
}
