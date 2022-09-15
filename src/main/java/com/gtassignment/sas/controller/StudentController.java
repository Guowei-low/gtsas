package com.gtassignment.sas.controller;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.dto.SuspendStudentParam;
import com.gtassignment.sas.exeption.ErrorResponse;
import com.gtassignment.sas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
public class StudentController extends BaseController{
    @Autowired
    StudentService studentService;

    @PostMapping(value = "/students")
    public ResponseEntity<Void> saveStudent(@Valid @RequestBody StudentParam studentParam) {
        Long studentId = studentService.saveStudent(studentParam);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/suspend")
    public ResponseEntity<Object> suspend(@Valid @RequestBody SuspendStudentParam suspendStudentParam)
            throws ErrorResponse {

       studentService.suspend(suspendStudentParam);

       return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
