package com.diary.diary.controller;

import com.diary.diary.exception.UserAlreadyExistsException;
import com.diary.diary.exception.UserNotFoundException;
import com.diary.diary.model.user.UserAddModel;
import com.diary.diary.model.user.UserUpdateModel;
import com.diary.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

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
}
