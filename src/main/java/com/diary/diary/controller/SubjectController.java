package com.diary.diary.controller;

import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.service.SubjectService;
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

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<Object> getSubjects() {
        return ResponseEntity.ok(subjectService.getSubjects());
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Object> getSubject(@PathVariable long subjectId) {
        try {
            return ResponseEntity.ok(subjectService.getSubject(subjectId));
        } catch (SubjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getSubject(@PathVariable String name) {
        try {
            return ResponseEntity.ok(subjectService.getSubject(name));
        } catch (SubjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
