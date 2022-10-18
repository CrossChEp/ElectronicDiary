package com.diary.diary.controller;

import com.diary.diary.context.UserClassContext;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.school_class.ClassGetByIdModel;
import com.diary.diary.model.school_class.ClassGetByNumberModel;
import com.diary.diary.service.ClassService;
import org.apache.commons.lang3.NotImplementedException;
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

    private final UserClassContext userClassContext;

    @Autowired
    public ClassController(UserClassContext userClassContext) {
        this.userClassContext = userClassContext;
    }

    @GetMapping
    public ResponseEntity<Object> getClasses() {
        return ResponseEntity.ok(userClassContext.getClasses());
    }

    @GetMapping("/number/{schoolNumber}/{classNumber}/{classLetter}")
    public ResponseEntity<Object> getSchoolClass(@PathVariable int schoolNumber,
                                                 @PathVariable int classNumber,
                                                 @PathVariable char classLetter) {
        try {
            ClassGetByNumberModel classGetByNumberModel =
                    new ClassGetByNumberModel(schoolNumber, classNumber, classLetter);
            return ResponseEntity.ok(userClassContext.getSchoolClass(classGetByNumberModel));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (Exception e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @GetMapping("{schoolNumberId}/{classNumber}/{classLetter}")
    public ResponseEntity<Object> getSchoolClassById(@PathVariable long schoolNumberId,
                                                     @PathVariable int classNumber,
                                                     @PathVariable char classLetter) {
        try {
            ClassGetByIdModel classData = new ClassGetByIdModel(schoolNumberId, classNumber, classLetter);
            return ResponseEntity.ok(userClassContext.getSchoolClassById(classData));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (Exception e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }

    @GetMapping("/homework")
    public ResponseEntity<Object> getHomework() {
        try {
            return ResponseEntity.ok(userClassContext.getHomework());
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (UserNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @GetMapping("/homework/{date}")
    public ResponseEntity<Object> getHomeworkByDate(@PathVariable String date) {
        try {
            return ResponseEntity.ok(userClassContext.getHomeworkByDate(date));
        }
        catch (UserNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        } catch (ParseException | NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }

    @GetMapping("/homework/subject/{subjectName}")
    public ResponseEntity<Object> getHomeworkBySubject(@PathVariable String subjectName) {
        try {
            return ResponseEntity.ok(userClassContext.getHomeworkBySubject(subjectName));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (Exception e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }
}
