package com.diary.diary.controller;

import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.school_class.ClassGetByIdModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/api/class")
public class ClassController {

    @Autowired
    private ClassService classService;

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

    @GetMapping("{schoolNumberId}/{classNumber}/{classLetter}")
    public ResponseEntity<Object> getSchoolClassById(@PathVariable long schoolNumberId,
                                                     @PathVariable int classNumber,
                                                     @PathVariable char classLetter) {
        try {
            ClassGetByIdModel classData = new ClassGetByIdModel(schoolNumberId, classNumber, classLetter);
            return ResponseEntity.ok(classService.getClassBySchoolId(classData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/homework")
    public ResponseEntity<Object> getHomework() {
        try {
            return ResponseEntity.ok(classService.getHomework());
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/homework/{date}")
    public ResponseEntity<Object> getHomeworkByDate(@PathVariable String date) {
        try {
            return ResponseEntity.ok(classService.getHomeworkByDate(date));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ParseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
