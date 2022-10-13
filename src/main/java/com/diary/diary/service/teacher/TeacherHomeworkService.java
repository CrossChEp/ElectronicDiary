package com.diary.diary.service.teacher;

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
import com.diary.diary.service.ClassService;
import com.diary.diary.service.HomeworkService;
import com.diary.diary.service.SubjectService;
import com.diary.diary.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherHomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepo;
    @Autowired
    private UserService userService;

    @Autowired
    private ClassService classService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private HomeworkService homeworkService;


    public HomeworkGetModel addHomework(HomeworkAddModel homeworkData)
            throws UserNotFoundException, ClassNotFoundException, SubjectNotFoundException {
        userService.checkUserRoleOrThrow(RoleNames.ADMIN, userService.getCurrentUser());
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
        HomeworkEntity homework = homeworkService.getHomework(homeworkId);
        ModelMapper mapper = new ModelMapper();
        mapper.map(newHomeworkData, homework);
        homeworkRepo.save(homework);
        return HomeworkGetModel.toModel(homework);
    }

    public HomeworkGetModel deleteHomework(long homeworkId) throws HomeworkNotFoundException {
        HomeworkEntity homework = homeworkService.getHomework(homeworkId);
        homeworkRepo.delete(homework);
        return HomeworkGetModel.toModel(homework);
    }
}
