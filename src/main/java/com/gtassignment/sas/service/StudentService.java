package com.gtassignment.sas.service;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.dto.SuspendStudentParam;
import com.gtassignment.sas.exeption.ErrorResponse;
import com.gtassignment.sas.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
    Long saveStudent(StudentParam studentParam);

    Long countStudentFromEmailList(List<String> studentEmailList);

    List<Student> getStudentByEmails(List<String> emails);

    List<Student> getCommonStudentByTeacherEmailList(List<String> teacherEmailList);

    void suspend(SuspendStudentParam suspendStudentParam) throws Exception;
}