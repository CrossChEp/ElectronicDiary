package com.diary.diary.model.school;

import com.diary.diary.entity.SchoolEntity;
import com.diary.diary.model.user.UserGetModel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

public class SchoolGetModel {
    @Getter @Setter
    private long id;

    @Getter @Setter
    private int number;

    @Getter @Setter
    private List<UserGetModel> students;
    
    public static SchoolGetModel toModel(SchoolEntity school) {
        ModelMapper mapper = new ModelMapper();
        SchoolGetModel model = new SchoolGetModel();
        mapper.map(school, model);

        return model;
    }
}
