package com.gtassignment.sas.controller;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.dto.SuspendStudentParam;
import com.gtassignment.sas.exeption.ErrorResponse;
import com.gtassignment.sas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class StudentController extends BaseController{
    @Autowired
    StudentService studentService;

    @PostMapping(value = "/students")
    public ResponseEntity<Void> saveStudent(@RequestBody StudentParam studentParam) {
        Long studentId = studentService.saveStudent(studentParam);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/suspend")
    public ResponseEntity<Object> suspend(@RequestBody SuspendStudentParam suspendStudentParam) throws ErrorResponse {
       if (!studentService.suspend(suspendStudentParam)) {
           throw new ErrorResponse("Unknown Error", HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
