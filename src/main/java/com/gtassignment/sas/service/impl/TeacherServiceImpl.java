package com.gtassignment.sas.service.impl;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.dto.TeacherParam;
import com.gtassignment.sas.model.Student;
import com.gtassignment.sas.model.Teacher;
import com.gtassignment.sas.repository.TeacherRepository;
import com.gtassignment.sas.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl extends BaseService implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeacher() {
        return null;
    }

    @Override
    public Long saveTeacher(TeacherParam teacherParam) {
        Teacher teacher = modelMapper.map(teacherParam, Teacher.class);
        return teacherRepository.save(teacher).getId();
    }
}
