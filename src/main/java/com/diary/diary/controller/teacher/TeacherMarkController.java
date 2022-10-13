package com.diary.diary.controller.teacher;

import com.diary.diary.model.mark.MarkAddModel;
import com.diary.diary.service.teacher.TeacherMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher/mark")
public class TeacherMarkController {
    @Autowired
    private TeacherMarkService teacherMarkService;

    @PostMapping
    public ResponseEntity<Object> addMark(@RequestBody MarkAddModel markData) {
        try {
            return ResponseEntity.ok(teacherMarkService.addMark(markData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
