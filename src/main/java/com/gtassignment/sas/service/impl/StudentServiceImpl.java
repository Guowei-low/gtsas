package com.gtassignment.sas.service.impl;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.dto.SuspendStudentParam;
import com.gtassignment.sas.exeption.ErrorResponse;
import com.gtassignment.sas.model.Student;
import com.gtassignment.sas.repository.StudentRepository;
import com.gtassignment.sas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl extends BaseService implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Long saveStudent(StudentParam studentParam) {
        Student student = modelMapper.map(studentParam, Student.class);
        return studentRepository.save(student).getId();
    }

    @Override
    public Long countStudentFromEmailList(List<String> studentEmailList) {
        return studentRepository.getExistingRecordCountFromEmails(studentEmailList);
    }

    @Override
    public List<Student> getStudentByEmails(List<String> emails) {
        return studentRepository.findStudentByEmails(emails);
    }

    @Override
    public List<Student> getCommonStudentByTeacherEmailList(List<String> teacherEmailList) {
            return studentRepository
                    .findCommonStudentsByTeacherEmails(teacherEmailList, Long.valueOf(teacherEmailList.size()));
    }

    @Override
    public void suspend(SuspendStudentParam suspendStudentParam) throws ErrorResponse {
        Student student = studentRepository.findByEmail(suspendStudentParam.getStudent());
        if(student == null) throw new ErrorResponse("Student is not registered");
        student.setSuspend(true);
        studentRepository.save(student);
    }
}
