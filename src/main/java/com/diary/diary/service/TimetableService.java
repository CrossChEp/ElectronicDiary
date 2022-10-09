package com.diary.diary.service;
import com.diary.diary.entity.TimetableEntity;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.timetable.TimetableNotFoundException;
import com.diary.diary.model.timetable.TimeTableUpdateModel;
import com.diary.diary.model.timetable.TimetableAddModel;
import com.diary.diary.model.timetable.TimetableGetModel;
import com.diary.diary.model.timetable.TimetableModel;
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


    public TimetableEntity addTimetable(TimetableAddModel timetableData) throws SubjectNotFoundException {
        validateModel(timetableData);
        TimetableEntity timetable = createTimetable(timetableData);
        timetableRepo.save(timetable);
        return timetable;
    }

    private void validateModel(TimetableAddModel timetableData) throws SubjectNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Map timetableModelJson = mapper.convertValue(timetableData, Map.class);
        isModelValid(timetableModelJson, false);
    }

    private void isModelValid(Map<String, List<String>> model, boolean id) throws SubjectNotFoundException {
        for(var day: model.keySet()) {
            if(model.get(day) == null || (id && day.equals("id"))) {
                continue;
            }
            for(var subjectName: model.get(day)) {
                subjectService.getSubjectEntity(subjectName);
            }
        }
    }

    private TimetableEntity createTimetable(TimetableAddModel timetableData) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        TimetableEntity timetable = new TimetableEntity();
        modelMapper.map(timetableData, timetable);
        return timetable;
    }

    public TimetableEntity updateTimetable(TimeTableUpdateModel newTimetableData)
            throws TimetableNotFoundException, SubjectNotFoundException {
        validateModel(newTimetableData);
        TimetableEntity timetable = getTimetable(newTimetableData.getId());
        updateTimetableEntity(newTimetableData, timetable);
        timetableRepo.save(timetable);
        return timetable;
    }

    private TimetableEntity getTimetable(long id) throws TimetableNotFoundException {
        return timetableRepo.findById(id)
                .orElseThrow(() -> new TimetableNotFoundException("timetable with id " + id + " not found"));
    }

    private void validateModel(TimeTableUpdateModel timetableData) throws SubjectNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Map timetableModelJson = mapper.convertValue(timetableData, Map.class);
        isModelValid(timetableModelJson, true);
    }

    private void updateTimetableEntity(TimeTableUpdateModel newTimetableData,
                                                  TimetableEntity timetable) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        TimetableModel timetableModel = new TimetableModel();
        mapper.map(newTimetableData, timetableModel);
        mapper.map(timetableModel, timetable);
    }

    public TimetableEntity deleteTimetable(long id) throws TimetableNotFoundException {
        TimetableEntity timetable = getTimetable(id);
        timetableRepo.delete(timetable);
        return timetable;
    }
}
