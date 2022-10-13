package com.diary.diary.model.homework;

import com.diary.diary.entity.ClassEntity;
import com.diary.diary.entity.SubjectEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassSubjectHomeworkModel {
    private ClassEntity schoolClass;
    private SubjectEntity subject;
    private HomeworkAddModel homeworkAddModel;

    public ClassSubjectHomeworkModel() {
    }

    public ClassSubjectHomeworkModel(ClassEntity schoolClass, SubjectEntity subject, HomeworkAddModel homeworkAddModel) {
        this.schoolClass = schoolClass;
        this.subject = subject;
        this.homeworkAddModel = homeworkAddModel;
    }
}
