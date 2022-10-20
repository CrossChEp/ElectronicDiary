package com.diary.diary.controller.admin;

import com.diary.diary.context.UserAdminContext;
import com.diary.diary.controller.ControllerServiceMethods;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.timetable.TimetableAlreadyExists;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.timetable.TimeTableUpdateModel;
import com.diary.diary.model.timetable.TimetableAddModel;
import com.diary.diary.model.timetable.TimetableClassModel;
import com.diary.diary.service.admin.AdminService;
import com.diary.diary.service.admin.AdminTimetableService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/timetable")
public class AdminTimetableController {

    private final UserAdminContext userAdminContext;

    @Autowired
    public AdminTimetableController(UserAdminContext userAdminContext) {
        this.userAdminContext = userAdminContext;
    }

    @PostMapping
    public ResponseEntity<Object> addTimetable(@RequestBody TimetableAddModel timetableData) {
        try {
            return ResponseEntity.ok(userAdminContext.addTimetable(timetableData));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (UserNotFoundException | SubjectNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateTimetable(@RequestBody TimeTableUpdateModel newTimetableData) {
        try {
            return ResponseEntity.ok(userAdminContext.updateTimetable(newTimetableData));
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

    @DeleteMapping("/{timetableId}")
    public ResponseEntity<Object> deleteTimetable(@PathVariable long timetableId) {
        try {
            return ResponseEntity.ok(userAdminContext.deleteTimetable(timetableId));
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

    @PostMapping("/class")
    public ResponseEntity<Object> addTimetableToClass(@RequestBody TimetableClassModel timetableClass) {
        try {
            return ResponseEntity.ok(userAdminContext.addTimetableToClass(timetableClass));
        }
        catch (TimetableAlreadyExists | NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (Exception e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @DeleteMapping("/class/{classId}")
    public ResponseEntity<Object> deleteTimetableFromClass(@PathVariable long classId) {
        try {
            return ResponseEntity.ok(userAdminContext.deleteTimetableFromClass(classId));
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
