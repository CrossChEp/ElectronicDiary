package com.diary.diary.model.homework;

import com.diary.diary.entity.HomeworkEntity;
import com.diary.diary.model.school_class.ClassGetModel;
import com.diary.diary.model.subject.SubjectGetModel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Getter @Setter
public class HomeworkGetModel {
    private long id;
    private SubjectGetModel subject;
    private ClassGetModel schoolClass;
    private String content;
    private Date date;

    public static HomeworkGetModel toModel(HomeworkEntity homework) {
        HomeworkGetModel model = new HomeworkGetModel();
        model.setSubject(SubjectGetModel.toModel(homework.getSubject()));
        model.setSchoolClass(ClassGetModel.toModel(homework.getSchoolClass()));
        model.setContent(homework.getContent());
        model.setDate(homework.getDate());
        return model;
    }
}
