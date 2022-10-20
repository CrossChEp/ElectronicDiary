package com.diary.diary.controller.admin;

import com.diary.diary.context.UserAdminContext;
import com.diary.diary.controller.ControllerServiceMethods;
import com.diary.diary.exception.school_class.ClassNotFoundException;
import com.diary.diary.exception.user.UserAlreadyExistsException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.admin.AdminAddUserToClassModel;
import com.diary.diary.model.admin.AdminRemoveUserFromClassModel;
import com.diary.diary.model.school_class.ClassAddModel;
import com.diary.diary.service.admin.AdminService;
import com.diary.diary.service.ClassService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/class")
public class AdminClassController {
    private final UserAdminContext userAdminContext;

    @Autowired
    public AdminClassController(UserAdminContext userAdminContext) {
        this.userAdminContext = userAdminContext;
    }

    @PostMapping
    public ResponseEntity<Object> addClass(@RequestBody ClassAddModel classData) {
        try {
            return ResponseEntity.ok(userAdminContext.addClass(classData));
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

    @PostMapping("/user")
    public ResponseEntity<Object> addUserToClass(@RequestBody AdminAddUserToClassModel userAndClassData) {
        try {
            return ResponseEntity.ok(userAdminContext.addUserToClass(userAndClassData));
        } catch (UserNotFoundException | ClassNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        } catch (UserAlreadyExistsException | NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Object> deleteClass(@PathVariable long classId) {
        try {
            return ResponseEntity.ok(userAdminContext.deleteClass(classId));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (ClassNotFoundException | UserNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<Object> removeUserFromClass(@RequestBody AdminRemoveUserFromClassModel userClasModel) {
        try {
            return ResponseEntity.ok(userAdminContext.removeUserFromClass(userClasModel));
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
