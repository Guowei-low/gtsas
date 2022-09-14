package com.gtassignment.sas.service.impl;

import com.gtassignment.sas.dto.RegisterStudentParam;
import com.gtassignment.sas.dto.TeacherParam;
import com.gtassignment.sas.model.Student;
import com.gtassignment.sas.model.Teacher;
import com.gtassignment.sas.repository.TeacherRepository;
import com.gtassignment.sas.service.StudentService;
import com.gtassignment.sas.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeacherServiceImpl extends BaseService implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentService studentService;

    @Override
    public List<Teacher> getAllTeacher() {
        return null;
    }

    @Override
    public Long saveTeacher(TeacherParam teacherParam) {
        Teacher teacher = modelMapper.map(teacherParam, Teacher.class);
        return teacherRepository.save(teacher).getId();
    }

    @Override
    public Teacher getTeacher(String email) {
        return teacherRepository.findByEmail(email);
    }

    @Override
    public Boolean registerStudent(RegisterStudentParam registerStudentParam) {
        try {
            Teacher teacher = teacherRepository.findByEmail(registerStudentParam.getTeacherEmail());
            List<Student> newStudents = studentService.getStudentByEmails(registerStudentParam.getStudentEmailList());
            Set<Student> existingStudents = new HashSet<>(teacher.getStudents());
            existingStudents.addAll(newStudents);
            teacher.setStudents(existingStudents);
            teacherRepository.save(teacher);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Long countTeacherFromEmailList(List<String> teacherEmailList) {
        return teacherRepository.getExistingRecordCountFromEmails(teacherEmailList);
    }
}
