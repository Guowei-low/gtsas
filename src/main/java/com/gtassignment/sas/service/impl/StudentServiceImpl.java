package com.gtassignment.sas.service.impl;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.model.Student;
import com.gtassignment.sas.repository.StudentRepository;
import com.gtassignment.sas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

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

    @Override
    public Long countStudentFromEmailList(List<StudentParam> studentParamList) {
        Type listType = new TypeToken<List<Student>>(){}.getType();
        List<Student> students = modelMapper.map(studentParamList, listType);
        List<String> emails = (List<String>) students.stream().map(student -> student.getEmail());
        return studentRepository.getExistingRecordCountFromEmails(emails);
    }
}
