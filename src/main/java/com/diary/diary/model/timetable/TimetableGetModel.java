package com.diary.diary.model.timetable;

import com.diary.diary.entity.TimetableEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Getter @Setter
public class TimetableGetModel extends TimetableAddModel {
    private long id;

    public static TimetableGetModel toModel(TimetableEntity timetable) {
        TimetableGetModel model = new TimetableGetModel();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(timetable, model);
        return model;
    }
}
