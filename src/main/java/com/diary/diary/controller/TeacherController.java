package com.diary.diary.controller;

import com.diary.diary.model.homework.HomeworkAddModel;
import com.diary.diary.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/homework")
    public ResponseEntity<Object> addHomework(@RequestBody HomeworkAddModel homeworkData) {
        try {
            return ResponseEntity.ok(teacherService.addHomework(homeworkData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
