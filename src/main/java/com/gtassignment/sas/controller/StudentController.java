package com.gtassignment.sas.controller;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping(value = "/students")
    public ResponseEntity<Void> saveStudent(@RequestBody StudentParam studentParam) {
        System.err.println("i am inside save students");
        Long studentId = studentService.saveStudent(studentParam);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentId).toUri();
        return ResponseEntity.created(location).build();
    }
}
