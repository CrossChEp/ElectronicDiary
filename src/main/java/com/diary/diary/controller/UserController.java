package com.diary.diary.controller;

import com.diary.diary.context.UserContext;
import com.diary.diary.exception.user.UserAlreadyExistsException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.mark.DateAndSubjectModel;
import com.diary.diary.model.user.UserAddModel;
import com.diary.diary.model.user.UserUpdateModel;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserContext userContext;

    @Autowired
    public UserController(UserContext userContext) {
        this.userContext = userContext;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserAddModel userData) {
        try {
            return ResponseEntity.ok(userContext.register(userData));
        } catch (UserAlreadyExistsException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }

    @GetMapping("/{userID}")
    public ResponseEntity<Object> getUser(@PathVariable long userID) {
        try {
            return ResponseEntity.ok(userContext.getUser(userID));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (UserNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok(userContext.getUsers());
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateModel newUserData) {
        try {
            return ResponseEntity.ok(userContext.updateUser(newUserData));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser() {
        try {
            return ResponseEntity.ok(userContext.deleteUser());
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (UserNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @GetMapping("/marks")
    public ResponseEntity<Object> getMarks() {
        try {
            return ResponseEntity.ok(userContext.getMarks());
        } catch (NotImplementedException e) {
            return new ResponseEntity<>("you have no rights", HttpStatus.FORBIDDEN);
        }
        catch (Exception e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @GetMapping("/marks/{date}")
    public ResponseEntity<Object> getMarksByDate(@PathVariable String date) {
        try {
            return ResponseEntity.ok(userContext.getMarksByDate(date));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        } catch (Exception e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @GetMapping("/marks/subject/{subjectName}")
    public ResponseEntity<Object> getMarksBySubject(@PathVariable String subjectName) {
        try {
            return ResponseEntity.ok(userContext.getMarksBySubject(subjectName));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        } catch (Exception e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @GetMapping("/marks/date_and_subject/{date}/{subject_name}")
    public ResponseEntity<Object> getMarksByDateAndSubject(@PathVariable String date,
                                                           @PathVariable String subject_name) {
        try {
            DateAndSubjectModel dateAndSubjectModel = new DateAndSubjectModel(date, subject_name);
            return ResponseEntity.ok(userContext.getMarksByDateAndSubject(dateAndSubjectModel));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (Exception e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }
}
