package com.diary.diary.controller.admin;

import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.timetable.TimeTableUpdateModel;
import com.diary.diary.model.timetable.TimetableAddModel;
import com.diary.diary.service.admin.AdminService;
import com.diary.diary.service.admin.AdminTimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/timetable")
public class AdminTimetableController {
    @Autowired
    private AdminTimetableService adminTimetableService;

    @PostMapping
    public ResponseEntity<Object> addTimetable(@RequestBody TimetableAddModel timetableData) {
        try {
            return ResponseEntity.ok(adminTimetableService.addTimetable(timetableData));
        } catch (UserNotFoundException | SubjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateTimetable(@RequestBody TimeTableUpdateModel newTimetableData) {
        try {
            return ResponseEntity.ok(adminTimetableService.updateTimetable(newTimetableData));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{timetableId}")
    public ResponseEntity<Object> deleteTimetable(@PathVariable long timetableId) {
        try {
            return ResponseEntity.ok(adminTimetableService.deleteTimetable(timetableId));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
