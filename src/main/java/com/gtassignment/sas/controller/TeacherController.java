package com.gtassignment.sas.controller;

import com.gtassignment.sas.dto.TeacherParam;
import com.gtassignment.sas.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;

    @PostMapping(value = "/teachers")
    public ResponseEntity<Void> saveTeacher(@RequestBody TeacherParam teacherParam) {
        Long teacherId = teacherService.saveTeacher(teacherParam);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacherId).toUri();
        return ResponseEntity.created(location).build();
    }
}