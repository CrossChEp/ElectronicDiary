package com.diary.diary.controller;

import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<Object> addClass(@RequestBody ClassAddModel classData) {
        try {
            return ResponseEntity.ok(classService.addClass(classData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getClasses() {
        return ResponseEntity.ok(classService.getClasses());
    }

    @GetMapping("/number/{schoolNumber}/{classNumber}/{classLetter}")
    public ResponseEntity<Object> getSchoolClass(@PathVariable int schoolNumber,
                                                 @PathVariable int classNumber,
                                                 @PathVariable char classLetter) {
        try {
            ClassGetByNumberModel classGetByNumberModel =
                    new ClassGetByNumberModel(schoolNumber, classNumber, classLetter);
            return ResponseEntity.ok(classService.getSchoolClass(classGetByNumberModel));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
