package com.diary.diary.controller;

import com.diary.diary.model.admin.AdminAddUserToSchoolModel;
import com.diary.diary.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/school")
    public ResponseEntity<Object> addUserToSchool(@RequestBody AdminAddUserToSchoolModel userAndSchoolData) {
        try {
            return ResponseEntity.ok(adminService.addUserToSchool(userAndSchoolData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
