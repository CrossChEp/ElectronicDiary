package com.diary.diary.model.mark;

import com.diary.diary.entity.MarkEntity;
import com.diary.diary.model.subject.SubjectGetModel;
import com.diary.diary.model.user.UserGetModel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Getter @Setter
public class MarkGetModel {
    private long id;
    private int mark;
    private int weight;
    private SubjectGetModel subject;
    private UserGetModel user;
    private String comment;
    private Date date;

    public static MarkGetModel toModel(MarkEntity mark) {
        MarkGetModel model = new MarkGetModel();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.map(mark, model);
        model.setSubject(SubjectGetModel.toModel(mark.getSubject()));
        model.setUser(UserGetModel.toModel(mark.getStudent()));
        return model;
    }
}
