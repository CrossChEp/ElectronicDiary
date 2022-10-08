package com.diary.diary.service;

import com.diary.diary.entity.SubjectEntity;
import com.diary.diary.exception.subject.SubjectAlreadyExistsException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.model.subject.SubjectAddModel;
import com.diary.diary.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepo;

    public SubjectEntity getSubjectById(long subjectId) throws SubjectNotFoundException {
        return subjectRepo.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("subject with such id doesn't exist"));
    }

    public SubjectEntity addSubject(SubjectAddModel subjectData) throws SubjectAlreadyExistsException {
        if(subjectRepo.findByName(subjectData.getName()) != null) {
            throw new SubjectAlreadyExistsException("subject with such name already exists");
        }
        SubjectEntity subject = new SubjectEntity();
        subject.setName(subjectData.getName());
        subjectRepo.save(subject);
        return subject;
    }
}
