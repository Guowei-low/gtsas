package com.gtassignment.sas.controller;

import com.gtassignment.sas.model.Student;
import com.gtassignment.sas.repository.TeacherRepository;
import com.gtassignment.sas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/home")
    public String GetTest()
    {
        return "Welcome";
    }

    @GetMapping(value = "/testing")
    public ResponseEntity<List<Student>> BookList(String genre) {
        var students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }
}