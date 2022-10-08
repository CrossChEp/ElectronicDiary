package com.diary.diary.controller.admin;

import com.diary.diary.entity.TimetableEntity;
import com.diary.diary.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/timetable")
public class AdminTimetableController {
    @Autowired
    private AdminService adminService;
}
