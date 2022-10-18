package com.diary.diary.service;

import com.diary.diary.config.RoleNames;
import com.diary.diary.entity.ClassEntity;
import com.diary.diary.entity.HomeworkEntity;
import com.diary.diary.entity.SubjectEntity;
import com.diary.diary.exception.homework.HomeworkNotFoundException;
import com.diary.diary.exception.school_class.ClassNotFoundException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.homework.ClassSubjectHomeworkModel;
import com.diary.diary.model.homework.HomeworkAddModel;
import com.diary.diary.model.homework.HomeworkGetModel;
import com.diary.diary.model.homework.HomeworkUpdateModel;
import com.diary.diary.repository.HomeworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class HomeworkService {


    private HomeworkRepository homeworkRepo;

    private ClassService classService;

    private SubjectService subjectService;

    public HomeworkService() {
    }

    public HomeworkService(ApplicationContext applicationContext) {
        homeworkRepo = applicationContext.getBean(HomeworkRepository.class);
        subjectService = new SubjectService(applicationContext);
        classService = new ClassService(applicationContext);
    }

    public HomeworkGetModel addHomework(HomeworkAddModel homeworkData)
            throws UserNotFoundException, ClassNotFoundException, SubjectNotFoundException {
        ClassEntity schoolClass = classService.getClassEntity(homeworkData.getClassId());
        SubjectEntity subject = subjectService.getSubjectById(homeworkData.getSubjectId());
        HomeworkEntity homework = generateHomeworkEntity(
                new ClassSubjectHomeworkModel(schoolClass, subject, homeworkData)
        );
        homeworkRepo.save(homework);
        return HomeworkGetModel.toModel(homework);
    }

    private HomeworkEntity generateHomeworkEntity(ClassSubjectHomeworkModel classSubjectHomeworkModel) {
        HomeworkEntity homework = new HomeworkEntity();
        homework.setSchoolClass(classSubjectHomeworkModel.getSchoolClass());
        homework.setSubject(classSubjectHomeworkModel.getSubject());
        homework.setContent(classSubjectHomeworkModel.getHomeworkAddModel().getContent());
        homework.setDate(classSubjectHomeworkModel.getHomeworkAddModel().getDate());
        return homework;
    }

    public HomeworkGetModel updateHomework(long homeworkId, HomeworkUpdateModel newHomeworkData)
            throws HomeworkNotFoundException {
        HomeworkEntity homework = getHomework(homeworkId);
        ModelMapper mapper = new ModelMapper();
        mapper.map(newHomeworkData, homework);
        homeworkRepo.save(homework);
        return HomeworkGetModel.toModel(homework);
    }

    public HomeworkGetModel deleteHomework(long homeworkId) throws HomeworkNotFoundException {
        HomeworkEntity homework = getHomework(homeworkId);
        homeworkRepo.delete(homework);
        return HomeworkGetModel.toModel(homework);
    }

    public HomeworkEntity getHomework(long id) throws HomeworkNotFoundException {
        return homeworkRepo.findById(id)
                .orElseThrow(() -> new HomeworkNotFoundException("homework with id " + id + " not found"));
    }
}
