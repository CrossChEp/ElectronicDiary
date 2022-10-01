package com.diary.diary.controller;

import com.diary.diary.exception.school.SchoolAlreadyExistsException;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public ResponseEntity<Object> addSchool(@RequestBody SchoolAddModel schoolData) {
        try {
            return ResponseEntity.ok(schoolService.addSchool(schoolData));
        } catch (SchoolAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<Object> getSchoolById(@PathVariable long schoolId) {
        try {
            return ResponseEntity.ok(schoolService.getSchoolById(schoolId));
        } catch (SchoolNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/number/{schoolNumber}")
    public ResponseEntity<Object> getSchoolByNumber(@PathVariable int schoolNumber) {
        try {
            return ResponseEntity.ok(schoolService.getSchoolByNumber(schoolNumber));
        } catch (SchoolNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getSchools() {
        return ResponseEntity.ok(schoolService.getSchools());
    }
}
