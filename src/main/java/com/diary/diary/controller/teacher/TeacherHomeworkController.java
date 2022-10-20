package com.diary.diary.controller.teacher;

import com.diary.diary.context.TeacherContext;
import com.diary.diary.model.homework.HomeworkAddModel;
import com.diary.diary.model.homework.HomeworkUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeacherHomeworkController {

    private final TeacherContext teacherContext;

    @Autowired
    public TeacherHomeworkController(TeacherContext teacherContext) {
        this.teacherContext = teacherContext;
    }

    @PostMapping("/homework")
    public ResponseEntity<Object> addHomework(@RequestBody HomeworkAddModel homeworkData) {
        try {
            return ResponseEntity.ok(teacherContext.addHomework(homeworkData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/homework/{homeworkId}")
    public ResponseEntity<Object> updateHomework(@PathVariable long homeworkId,
                                                 @RequestBody HomeworkUpdateModel newHomeworkData) {
        try {
            return ResponseEntity.ok(teacherContext.updateHomework(homeworkId, newHomeworkData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/homework/{homeworkId}")
    public ResponseEntity<Object> deleteHomework(@PathVariable long homeworkId) {
        try {
            return ResponseEntity.ok(teacherContext.deleteHomework(homeworkId));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
