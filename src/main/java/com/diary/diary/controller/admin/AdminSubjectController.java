package com.diary.diary.controller.admin;

import com.diary.diary.context.UserAdminContext;
import com.diary.diary.controller.ControllerServiceMethods;
import com.diary.diary.exception.model.InvalidModelDataException;
import com.diary.diary.exception.subject.SubjectAlreadyExistsException;
import com.diary.diary.exception.subject.SubjectNotFoundException;
import com.diary.diary.exception.user.UserNotFoundException;
import com.diary.diary.model.subject.SubjectAddModel;
import com.diary.diary.model.subject.SubjectDeleteModel;
import com.diary.diary.model.subject.SubjectUpdateModel;
import com.diary.diary.service.SubjectService;
import com.diary.diary.service.admin.AdminSubjectService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/subject")
public class AdminSubjectController {

    private final UserAdminContext userAdminContext;

    @Autowired
    public AdminSubjectController(UserAdminContext userAdminContext) {
        this.userAdminContext = userAdminContext;
    }

    @PostMapping
    public ResponseEntity<Object> addSubject(@RequestBody SubjectAddModel subjectData) {
        try {
            return ResponseEntity.ok(userAdminContext.addSubject(subjectData));
        }
        catch(SubjectAlreadyExistsException | NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
        catch (UserNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateSubject(@RequestBody SubjectUpdateModel subjectNewData) {
        try {
            return ResponseEntity.ok(userAdminContext.updateSubject(subjectNewData));
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

    @DeleteMapping
    public ResponseEntity<Object> deleteSubject(@RequestBody SubjectDeleteModel subjectDeleteData) {
        try {
            return ResponseEntity.ok(userAdminContext.deleteSubject(subjectDeleteData));
        } catch (UserNotFoundException | SubjectNotFoundException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.NOT_FOUND, "404", e.getMessage());
        } catch (InvalidModelDataException | NotImplementedException e) {
            return ControllerServiceMethods
                    .getErrorResponse(HttpStatus.FORBIDDEN, "403", e.getMessage());
        }
    }
}
