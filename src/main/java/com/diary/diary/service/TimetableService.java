package com.diary.diary.service;
import com.diary.diary.entity.TimetableEntity;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.model.timetable.TimetableAddModel;
import com.diary.diary.repository.SubjectRepository;
import com.diary.diary.repository.TimetableRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TimetableService {
    @Autowired
    private TimetableRepository timetableRepo;
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    public TimetableEntity addTimetable(TimetableAddModel timetableData) throws SubjectNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Map timetableModelJson = mapper.convertValue(timetableData, Map.class);
        isModelValid(timetableModelJson);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        TimetableEntity timetable = new TimetableEntity();
        modelMapper.map(timetableData, timetable);
        timetableRepo.save(timetable);
        return timetable;
    }

    private void isModelValid(Map<String, List<String>> model) throws SubjectNotFoundException {
        for(var day: model.keySet()) {
            if(model.get(day) == null) {
                continue;
            }
            for(var subjectName: model.get(day)) {
                subjectService.getSubjectEntity(subjectName);
            }
        }
    }
}
