package com.diary.diary.controller.admin;

import com.diary.diary.exception.school_class.ClassNotFoundException;
import com.diary.diary.exception.user.UserAlreadyExistsException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.admin.AdminAddUserToClassModel;
import com.diary.diary.model.admin.AdminRemoveUserFromClassModel;
import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.service.AdminService;
import com.diary.diary.service.ClassService;
import com.diary.diary.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/class")
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

    @PostMapping("/user")
    public ResponseEntity<Object> addUserToClass(@RequestBody AdminAddUserToClassModel userAndClassData) {
        try {
            return ResponseEntity.ok(adminService.addUserToClass(userAndClassData));
        } catch (UserNotFoundException | ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Object> deleteClass(@PathVariable long classId) {
        try {
            return ResponseEntity.ok(classService.deleteClass(classId));
        } catch (ClassNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<Object> removeUserFromSchool(@RequestBody AdminRemoveUserFromClassModel userClasModel) {
        try {
            return ResponseEntity.ok(adminService.removeUserFromSchool(userClasModel));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
