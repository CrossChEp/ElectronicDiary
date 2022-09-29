package com.diary.diary.controller;

import com.diary.diary.exception.UserAlreadyExistsException;
import com.diary.diary.model.user.UserAddModel;
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
        return ResponseEntity.ok(userService.getUser(userID));
    }
}
