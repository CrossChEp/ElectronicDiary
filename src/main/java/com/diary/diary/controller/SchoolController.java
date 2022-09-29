package com.diary.diary.controller;

import com.diary.diary.exception.school.SchoolAlreadyExistsException;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
