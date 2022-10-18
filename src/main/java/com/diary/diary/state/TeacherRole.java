package com.diary.diary.state;

import com.diary.diary.model.mark.MarkAddModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.service.MarkService;
import com.diary.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class TeacherRole extends StudentRole {

    private final MarkService markService;

    @Autowired
    public TeacherRole(ApplicationContext applicationContext) {
        this.markService = new MarkService(applicationContext);
    }

    @Override
    public MarkGetModel addMark(MarkAddModel markData) {
        return markService.addMark(markData);
    }

    @Override
    public MarkGetModel removeMark(long id) {
        return markService.removeMark(id);
    }

    @Override
    public MarkGetModel updateMark(long markId, int mark) {
        return markService.updateMark(markId, mark);
    }
}
