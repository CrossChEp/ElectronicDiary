package com.diary.diary.entity;

import com.diary.diary.converter.ListToStingConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "timetables", schema = "working_schema")
@TypeDef(
        name = "list-array",
        typeClass = List.class
)
public class TimetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter @Setter
    private long id;

    @Getter @Setter @Convert(converter = ListToStingConverter.class)
    private List<String> monday;

    @Getter @Setter @Convert(converter = ListToStingConverter.class)
    private List<String> tuesday;

    @Getter @Setter @Convert(converter = ListToStingConverter.class)
    private List<String> wednesday;

    @Getter @Setter @Convert(converter = ListToStingConverter.class)
    private List<String> thursday;

    @Getter @Setter @Convert(converter = ListToStingConverter.class)
    private List<String> friday;

    @Getter @Setter @Convert(converter = ListToStingConverter.class)
    private List<String> saturday;

    @Getter @Setter @Convert(converter = ListToStingConverter.class)
    private List<String> sunday;

    @OneToOne(mappedBy = "timetable")
    @Getter @Setter
    private ClassEntity schoolClass;

    public TimetableEntity() {
    }
}
