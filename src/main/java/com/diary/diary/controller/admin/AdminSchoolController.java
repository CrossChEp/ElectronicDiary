package com.diary.diary.controller.admin;

import com.diary.diary.context.UserAdminContext;
import com.diary.diary.controller.ControllerServiceMethods;
import com.diary.diary.exception.school.SchoolAlreadyExistsException;
import com.diary.diary.exception.school.SchoolNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.admin.AdminAddUserToSchoolModel;
import com.diary.diary.model.admin.AdminRemoveUserFromSchoolModel;
import com.diary.diary.model.school.SchoolAddModel;
import com.diary.diary.service.admin.AdminService;
import com.diary.diary.service.SchoolService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/school")
public class AdminSchoolController {
    private final UserAdminContext userAdminContext;

    @Autowired
    public AdminSchoolController(UserAdminContext userAdminContext) {
        this.userAdminContext = userAdminContext;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> addUserToSchool(@RequestBody AdminAddUserToSchoolModel userAndSchoolData) {
        try {
            return ResponseEntity.ok(userAdminContext.addUserToSchool(userAndSchoolData));
        } catch (Exception e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addSchool(@RequestBody SchoolAddModel schoolData) {
        try {
            return ResponseEntity.ok(userAdminContext.addSchool(schoolData));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (SchoolAlreadyExistsException | UserNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @DeleteMapping("/{schoolId}")
    public ResponseEntity<Object> deleteSchool(@PathVariable long schoolId) {
        try {
            return ResponseEntity.ok(userAdminContext.deleteSchool(schoolId));
        }
        catch (NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (SchoolNotFoundException | UserNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<Object> removeStudentFromSchool(@RequestBody AdminRemoveUserFromSchoolModel studentSchoolModel) {
        try {
            return ResponseEntity.ok(userAdminContext.removeUserFromSchool(studentSchoolModel));
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
