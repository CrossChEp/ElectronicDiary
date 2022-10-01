package com.diary.diary.model.school_class;

import com.diary.diary.entity.ClassEntity;
import com.diary.diary.model.school.SchoolGetModel;
import com.diary.diary.model.user.UserGetModel;
import com.diary.diary.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClassGetModel {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private int number;

    @Getter @Setter
    private char letter;

    @Getter @Setter
    private SchoolGetModel school;

    @Getter @Setter
    private List<UserGetModel> students;

    public static ClassGetModel toModel(ClassEntity schoolClass) {
        ClassGetModel model = new ClassGetModel();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(schoolClass, model);
        model.setSchool(SchoolGetModel.toModel(schoolClass.getSchool()));
        UserService userService = new UserService();
        model.setStudents(userService.convertToUserGetModelList(schoolClass.getStudents()));
        return model;
    }
}
