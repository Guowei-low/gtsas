package com.gtassignment.sas.service;

import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.dto.TeacherParam;
import com.gtassignment.sas.model.Student;
import com.gtassignment.sas.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeacher();
    Long saveTeacher(TeacherParam teacherParam);
}
