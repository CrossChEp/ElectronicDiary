package com.diary.diary.controller;

import com.diary.diary.context.UserContext;
import com.diary.diary.exception.user.UserAlreadyExistsException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.mark.DateAndSubjectModel;
import com.diary.diary.model.user.UserAddModel;
import com.diary.diary.model.user.UserUpdateModel;
import com.diary.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserContext userContext;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserAddModel userData) {
        try {
            return ResponseEntity.ok(userService.addUser(userData));
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{userID}")
    public ResponseEntity<Object> getUser(@PathVariable long userID) {
        try {
            return ResponseEntity.ok(userService.getUser(userID));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateModel newUserData) {
        try {
            return ResponseEntity.ok(userService.updateUser(newUserData));
        }
        catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser() {
        try {
            return ResponseEntity.ok(userService.deleteUser());
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/marks")
    public ResponseEntity<Object> getMarks() {
        try {
            UserContext userContext = new UserContext(userService);
            return ResponseEntity.ok(userContext.getMarks());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/marks/{date}")
    public ResponseEntity<Object> getMarksByDate(@PathVariable String date) {
        try {
            return ResponseEntity.ok(userService.getMarksByDate(date));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/marks/subject/{subjectName}")
    public ResponseEntity<Object> getMarksBySubject(@PathVariable String subjectName) {
        try {
            return ResponseEntity.ok(userService.getMarksBySubject(subjectName));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/marks/date_and_subject/{date}/{subject_name}")
    public ResponseEntity<Object> getMarksByDateAndSubject(@PathVariable String date,
                                                           @PathVariable String subject_name) {
        try {
            DateAndSubjectModel dateAndSubjectModel = new DateAndSubjectModel(date, subject_name);
            return ResponseEntity.ok(userService.getMarksByDateAndSubject(dateAndSubjectModel));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
