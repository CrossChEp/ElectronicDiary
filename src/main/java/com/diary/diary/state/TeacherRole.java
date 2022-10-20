package com.diary.diary.state;

import com.diary.diary.model.homework.HomeworkAddModel;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.homework.HomeworkUpdateModel;
import com.diary.diary.model.mark.MarkAddModel;
import com.diary.diary.model.mark.MarkGetModel;
import com.diary.diary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class TeacherRole extends StudentRole {

    private final MarkService markService;
    private final HomeworkService homeworkService;
    @Autowired
    public TeacherRole(ApplicationContext applicationContext) {
        super(applicationContext);
        this.markService = new MarkService(applicationContext);
        this.homeworkService = new HomeworkService(applicationContext);
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

    @Override
    public HomeworkGetModel addHomework(HomeworkAddModel homeworkData) {
        return homeworkService.addHomework(homeworkData);
    }

    @Override
    public HomeworkGetModel updateHomework(long homeworkId, HomeworkUpdateModel homeworkNewData) {
        return homeworkService.updateHomework(homeworkId, homeworkNewData);
    }

    @Override
    public HomeworkGetModel deleteHomework(long id) {
        return homeworkService.deleteHomework(id);
    }
}
