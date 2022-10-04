package com.diary.diary.controller.admin;

import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.service.AdminService;
import com.diary.diary.service.ClassService;
import com.diary.diary.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/class")
public class AdminClassController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<Object> addClass(@RequestBody ClassAddModel classData) {
        try {
            return ResponseEntity.ok(classService.addClass(classData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
