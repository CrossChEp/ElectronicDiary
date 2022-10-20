package com.diary.diary.controller;

import com.diary.diary.context.UserSchoolContext;
import com.diary.diary.exception.school.SchoolAlreadyExistsException;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.service.SchoolService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
    private final UserSchoolContext userSchoolContext;

    @Autowired
    public SchoolController(UserSchoolContext userSchoolContext) {
        this.userSchoolContext = userSchoolContext;
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<Object> getSchoolById(@PathVariable long schoolId) {
        try {
            return ResponseEntity.ok(userSchoolContext.getSchoolById(schoolId));
        }
        catch (SchoolNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }

    @GetMapping("/number/{schoolNumber}")
    public ResponseEntity<Object> getSchoolByNumber(@PathVariable int schoolNumber) {
        try {
            return ResponseEntity.ok(userSchoolContext.getSchoolByNumber(schoolNumber));
        }
        catch (SchoolNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getSchools() {
        try {
            return ResponseEntity.ok(userSchoolContext.getSchools());
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }
}
