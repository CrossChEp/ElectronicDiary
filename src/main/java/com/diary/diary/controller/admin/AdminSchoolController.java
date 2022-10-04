package com.diary.diary.controller.admin;

import com.diary.diary.exception.school.SchoolAlreadyExistsException;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.admin.AdminAddUserToSchoolModel;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.service.AdminService;
import com.diary.diary.service.ClassService;
import com.diary.diary.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/school")
public class AdminSchoolController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/user")
    public ResponseEntity<Object> addUserToSchool(@RequestBody AdminAddUserToSchoolModel userAndSchoolData) {
        try {
            return ResponseEntity.ok(adminService.addUserToSchool(userAndSchoolData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addSchool(@RequestBody SchoolAddModel schoolData) {
        try {
            return ResponseEntity.ok(schoolService.addSchool(schoolData));
        } catch (SchoolAlreadyExistsException | UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{schoolId}")
    public ResponseEntity<Object> deleteSchool(@PathVariable long schoolId) {
        try {
            return ResponseEntity.ok(schoolService.deleteSchool(schoolId));
        } catch (SchoolNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
