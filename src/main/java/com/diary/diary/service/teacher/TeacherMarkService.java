package com.diary.diary.service.teacher;

import com.diary.diary.config.DateConfig;
import com.diary.diary.entity.MarkEntity;
import com.diary.diary.exception.mark.MarkNotFoundException;
import com.diary.diary.model.mark.MarkAddModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherMarkService {
    @Autowired
    private MarkService markService;

    public MarkGetModel addMark(MarkAddModel markData) throws Exception {
        return markService.addMark(markData);
    }

    public MarkGetModel removeMark(long markId) throws MarkNotFoundException {
        return MarkGetModel.toModel(markService.removeMark(markId));
    }

    public MarkGetModel updateMark(long markId, int newMark) throws Exception {
        return MarkGetModel.toModel(markService.updateMark(markId, newMark));
    }
}
