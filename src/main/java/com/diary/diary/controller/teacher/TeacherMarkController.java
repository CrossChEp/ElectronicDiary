package com.diary.diary.controller.teacher;

import com.diary.diary.context.TeacherContext;
import com.diary.diary.controller.ControllerServiceMethods;
import com.diary.diary.model.mark.MarkAddModel;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/mark")
public class TeacherMarkController {

    private final TeacherContext teacherContext;

    @Autowired
    public TeacherMarkController(TeacherContext teacherContext) {
        this.teacherContext = teacherContext;
    }

    @PostMapping
    public ResponseEntity<Object> addMark(@RequestBody MarkAddModel markData) {
        try {
            return ResponseEntity.ok(teacherContext.addMark(markData));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods.
                    getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (Exception e) {
            return ControllerServiceMethods.
                    getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @DeleteMapping("/{markId}")
    public ResponseEntity<Object> removeMark(@PathVariable long markId) {
        try {
            return ResponseEntity.ok(teacherContext.removeMark(markId));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods.
                    getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (Exception e) {
            return ControllerServiceMethods.
                    getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @PutMapping("/{markId}")
    public ResponseEntity<Object> updateMark(@PathVariable long markId, @RequestParam int newMark) {
        try {
            return ResponseEntity.ok(teacherContext.updateMark(markId, newMark));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods.
                    getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (Exception e) {
            return ControllerServiceMethods.
                    getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }
}
