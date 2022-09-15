package com.gtassignment.sas.controller;

import com.gtassignment.sas.dto.RegisterStudentParam;
import com.gtassignment.sas.dto.TeacherParam;
import com.gtassignment.sas.exeption.ErrorResponse;
import com.gtassignment.sas.model.Teacher;
import com.gtassignment.sas.service.StudentService;
import com.gtassignment.sas.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@Validated
public class TeacherController extends BaseController{
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @PostMapping(value = "/teachers")
    public ResponseEntity<Void> saveTeacher(@Valid @RequestBody TeacherParam teacherParam) {
        Long teacherId = teacherService.saveTeacher(teacherParam);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacherId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> registerStudents(@Valid @RequestBody RegisterStudentParam registerStudentParam)
            throws ErrorResponse {
        Teacher teacher = teacherService.getTeacher(registerStudentParam.getTeacherEmail());
        if (teacher == null) {
            //return Error code
            throw new ErrorResponse("Teacher Not Found", HttpStatus.BAD_REQUEST);
        }
        //empty email
        if (registerStudentParam.getStudentEmailList().isEmpty()) {
            throw new ErrorResponse("Empty student email list", HttpStatus.BAD_REQUEST);
        }
        //some student wasn't registered
        Long countExistingStudent = studentService
                .countStudentFromEmailList(registerStudentParam.getStudentEmailList());
        if (registerStudentParam.getStudentEmailList().size() != countExistingStudent) {
            throw new ErrorResponse("Some student's email was not registered", HttpStatus.BAD_REQUEST);
        }

        teacherService.registerStudent(registerStudentParam);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/commonstudents")
    public ResponseEntity<Object> findCommonStudent(@Valid @RequestParam(name = "teacher") List<@Email String> teacherEmailList)
            throws ErrorResponse {
        if (teacherEmailList.isEmpty()) {
            throw new ErrorResponse("No teacher email provided", HttpStatus.BAD_REQUEST);
        }

        if (teacherService.countTeacherFromEmailList(teacherEmailList) != teacherEmailList.size()) {
            throw new ErrorResponse("Some teacher's email wasn't registered", HttpStatus.BAD_REQUEST);
        }

        List<String> students = studentService.getCommonStudentByTeacherEmailList(teacherEmailList).stream()
                .map(student -> student.getEmail()).toList();

        return ResponseEntity.ok().body(Collections.singletonMap("students", students));
    }
}