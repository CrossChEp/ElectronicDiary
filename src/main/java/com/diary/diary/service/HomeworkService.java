package com.diary.diary.service;

import com.diary.diary.entity.HomeworkEntity;
import com.diary.diary.exception.homework.HomeworkNotFoundException;
import com.diary.diary.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkService {

    @Autowired
    private HomeworkRepository homeworkRepo;

    public HomeworkEntity getHomework(long id) throws HomeworkNotFoundException {
        return homeworkRepo.findById(id)
                .orElseThrow(() -> new HomeworkNotFoundException("homework with id " + id + " not found"));
    }
}
