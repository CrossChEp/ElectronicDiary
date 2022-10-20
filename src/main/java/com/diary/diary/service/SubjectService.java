package com.diary.diary.service;

import com.diary.diary.entity.SubjectEntity;
import com.diary.diary.exception.model.InvalidModelDataException;
import com.diary.diary.exception.subject.SubjectAlreadyExistsException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.model.subject.SubjectAddModel;
import com.diary.diary.model.subject.SubjectDeleteModel;
import com.diary.diary.model.subject.SubjectGetModel;
import com.diary.diary.model.subject.SubjectUpdateModel;
import com.diary.diary.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private SubjectRepository subjectRepo;

    public SubjectService() {
    }

    public SubjectService(ApplicationContext applicationContext) {
        this.subjectRepo = applicationContext.getBean(SubjectRepository.class);
    }

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

    public List<SubjectGetModel> getSubjects() {
        List<SubjectEntity> subjects = subjectRepo.findAll();
        return generateSubjectGetModelList(subjects);
    }

    private List<SubjectGetModel> generateSubjectGetModelList(List<SubjectEntity> subjects) {
        return subjects.stream().map(SubjectGetModel::toModel).toList();
    }

    public SubjectGetModel getSubject(long id) throws SubjectNotFoundException {
        SubjectEntity subject =  subjectRepo.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("subject with such id doesn't exist"));
        return SubjectGetModel.toModel(subject);
    }

    public SubjectGetModel getSubject(String name) throws SubjectNotFoundException {
        SubjectEntity subject = Optional.ofNullable(subjectRepo.findByName(name))
                .orElseThrow(() -> new SubjectNotFoundException("subject with such name doesn't exists"));
        return SubjectGetModel.toModel(subject);
    }

    public SubjectEntity getSubjectEntity(String name) throws SubjectNotFoundException {
        return Optional.ofNullable(subjectRepo.findByName(name))
                .orElseThrow(() -> new SubjectNotFoundException("subject with such name doesn't exists"));
    }

    public SubjectEntity updateSubject(SubjectUpdateModel newSubjectData) throws SubjectNotFoundException {
        SubjectEntity subject = getSubjectById(newSubjectData.getId());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(newSubjectData, subject);
        subjectRepo.save(subject);
        return subject;
    }

    public SubjectEntity deleteSubject(SubjectDeleteModel subjectDeleteData)
            throws InvalidModelDataException, SubjectNotFoundException {
        if(subjectDeleteData.getName() == null && subjectDeleteData.getId() == null) {
            throw new InvalidModelDataException("the model is null");
        }
        SubjectEntity subject = getSubjectInDependenceOfModel(subjectDeleteData);
        subjectRepo.delete(subject);
        return subject;
    }

    private SubjectEntity getSubjectInDependenceOfModel(SubjectDeleteModel subjectDeleteData)
            throws SubjectNotFoundException {
        return subjectDeleteData.getId() == null
                ? getSubjectEntity(subjectDeleteData.getName()) : getSubjectById(subjectDeleteData.getId());
    }
}
