package com.gtassignment.sas.service.impl;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.model.Student;
import com.gtassignment.sas.repository.StudentRepository;
import com.gtassignment.sas.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Long saveStudent(StudentParam studentParam) {
        Student student = modelMapper.map(studentParam, Student.class);
        return studentRepository.save(student).getId();
    }
}
