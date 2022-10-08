package com.diary.diary.controller.admin;

import com.diary.diary.exception.model.InvalidModelDataException;
import com.diary.diary.exception.subject.SubjectAlreadyExistsException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.subject.SubjectAddModel;
import com.diary.diary.model.subject.SubjectDeleteModel;
import com.diary.diary.model.subject.SubjectUpdateModel;
import com.diary.diary.service.SubjectService;
import com.diary.diary.service.admin.AdminSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/subject")
public class AdminSubjectController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private AdminSubjectService adminSubjectService;

    @PostMapping
    public ResponseEntity<Object> addSubject(@RequestBody SubjectAddModel subjectData) {
        try {
            return ResponseEntity.ok(subjectService.addSubject(subjectData));
        } catch(SubjectAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateSubject(@RequestBody SubjectUpdateModel subjectNewData) {
        try {
            return ResponseEntity.ok(subjectService.updateSubject(subjectNewData));
        } catch (SubjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteSubject(@RequestBody SubjectDeleteModel subjectDeleteData) {
        try {
            return ResponseEntity.ok(adminSubjectService.deleteSubject(subjectDeleteData));
        } catch (UserNotFoundException | SubjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InvalidModelDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
