package com.gtassignment.sas.service;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
    Long saveStudent(StudentParam studentParam);

    Long countStudentFromEmailList(List<StudentParam> studentParamList);
}