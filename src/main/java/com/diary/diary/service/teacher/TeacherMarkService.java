package com.diary.diary.service.teacher;

import com.diary.diary.model.mark.MarkAddModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherMarkService {
    @Autowired
    private MarkService markService;

    public MarkGetModel addMark(MarkAddModel markData) throws Exception {
        return markService.addMark(markData);
    }
}
