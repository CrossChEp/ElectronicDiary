package com.diary.diary.controller;

import com.diary.diary.context.UserSubjectContext;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final UserSubjectContext userSubjectContext;

    @Autowired
    public SubjectController(UserSubjectContext userSubjectContext) {
        this.userSubjectContext = userSubjectContext;
    }

    @GetMapping
    public ResponseEntity<Object> getSubjects() {
        return ResponseEntity.ok(userSubjectContext.getSubjects());
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Object> getSubject(@PathVariable long subjectId) {
        try {
            return ResponseEntity.ok(userSubjectContext.getSubject(subjectId));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (SubjectNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getSubject(@PathVariable String name) {
        try {
            return ResponseEntity.ok(userSubjectContext.getSubject(name));
        }
        catch (SubjectNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }
}
