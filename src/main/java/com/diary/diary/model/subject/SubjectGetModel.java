package com.diary.diary.model.subject;

import com.diary.diary.entity.SubjectEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SubjectGetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;

    public static SubjectGetModel toModel(SubjectEntity subject) {
        ModelMapper mapper = new ModelMapper();
        SubjectGetModel model = new SubjectGetModel();
        mapper.map(subject, model);
        return model;
    }
}
