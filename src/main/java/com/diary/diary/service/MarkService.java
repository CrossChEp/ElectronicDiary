package com.diary.diary.service;

import com.diary.diary.config.DateConfig;
import com.diary.diary.entity.MarkEntity;
import com.diary.diary.entity.SubjectEntity;
import com.diary.diary.entity.UserEntity;
import com.diary.diary.exception.mark.MarkNotFoundException;
import com.diary.diary.exception.model.InvalidModelDataException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.mark.MarkAddModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.repository.MarkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarkService {

    @Autowired
    private MarkRepository markRepo;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    public MarkGetModel addMark(MarkAddModel markData)
            throws SubjectNotFoundException, UserNotFoundException, InvalidModelDataException {
        if(markData.getMark() < 2 || markData.getMark() > 5
                || markData.getWeight() > 3 || markData.getWeight() < 1) {
            throw new InvalidModelDataException("mark data is invalid");
        }
        MarkEntity mark = new MarkEntity();
        SubjectEntity subject = subjectService.getSubjectEntity(markData.getSubjectName());
        UserEntity student = userService.getUserEntity(markData.getUserId());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.map(markData, mark);
        mark.setSubject(subject);
        mark.setStudent(student);
        markRepo.save(mark);
        return MarkGetModel.toModel(mark);
    }

    public MarkEntity removeMark(long id) throws MarkNotFoundException {
        MarkEntity mark = getMark(id);
        markRepo.delete(mark);
        return mark;
    }

    public MarkEntity getMark(long id) throws MarkNotFoundException {
        return markRepo.findById(id)
                .orElseThrow(() -> new MarkNotFoundException("mark with id " + id + " doesn't exist"));
    }
}
