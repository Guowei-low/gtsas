package com.gtassignment.sas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtassignment.sas.dto.RegisterStudentParam;
import com.gtassignment.sas.dto.TeacherParam;
import com.gtassignment.sas.model.Student;
import com.gtassignment.sas.model.Teacher;
import com.gtassignment.sas.service.StudentService;
import com.gtassignment.sas.service.TeacherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test()
    @DisplayName(value = "Shoud return 201 when valid teacher data posted")
    void testSaveTeacherReturnStatusCodeWhenSuccess() throws Exception {

        //given
        TeacherParam teacherDto = TeacherParam
                .builder().email("studentmary@gmail.com").name("Mary").build();
        String json = objectMapper.writeValueAsString(teacherDto);
        when(this.teacherService.saveTeacher(teacherDto)).thenReturn(0L);

        this.mockMvc.perform(post("/teachers")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test()
    @DisplayName(value = "Should return 400 when invalid teacher's email posted")
    void testSaveTeacherReturnStatusCodeWhenFailed() throws Exception {
        //given
        TeacherParam teacherDto = TeacherParam
                .builder().email("studentmarygmail.com").name("Mary").build();
        String json = objectMapper.writeValueAsString(teacherDto);
        when(this.teacherService.saveTeacher(teacherDto)).thenReturn(0L);

        this.mockMvc.perform(post("/teachers")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", hasItem("Email is not valid")));
    }

    @Test()
    @DisplayName(value = "Should return 201 when valid teacher's email and valid student email list posted")
    void testRegisterStudentsToTeacherReturnStatusCodeWhenSuccess() throws Exception {
        RegisterStudentParam registerStudentDto = RegisterStudentParam
                .builder().teacherEmail("teacher1@gmail.com")
                .studentEmailList(Arrays.asList("student1@gmail.com","student2@gmail.com")).build();
        String json = objectMapper.writeValueAsString(registerStudentDto);

        Teacher teacher1 = Teacher.builder().email("teacher1@gmail.com").name("tea").build();

        when(this.teacherService.getTeacher(teacher1.getEmail())).thenReturn(teacher1);
        when(this.studentService.countStudentFromEmailList(registerStudentDto.getStudentEmailList()))
                .thenReturn(Long.valueOf("2"));

        when(this.teacherService.registerStudent(registerStudentDto)).thenReturn(true);

        this.mockMvc.perform(post("/register")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test()
    @DisplayName(value = "Should return 400 when invalid teacher's email and valid student email list posted")
    void testRegisterStudentsToTeacherReturnStatusCodeWhenFailure() throws Exception {
        RegisterStudentParam registerStudentDto = RegisterStudentParam
                .builder().teacherEmail("teacher1gmail.com")
                .studentEmailList(Arrays.asList("student1@gmail.com","student2@gmail.com")).build();
        String json = objectMapper.writeValueAsString(registerStudentDto);

        Teacher teacher1 = Teacher.builder().email("teacher1@gmail.com").name("tea").build();

        when(this.teacherService.getTeacher(teacher1.getEmail())).thenReturn(teacher1);
        when(this.studentService.countStudentFromEmailList(registerStudentDto.getStudentEmailList()))
                .thenReturn(Long.valueOf("2"));

        when(this.teacherService.registerStudent(registerStudentDto)).thenReturn(true);

        this.mockMvc.perform(post("/register")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", hasItem("Email is not valid")));
    }

    @Test()
    @DisplayName(value = "Should return 400 when empty data posted")
    void testRegisterStudentsToTeacherReturnStatusCodeWhenFailure1() throws Exception {
        RegisterStudentParam registerStudentDto = RegisterStudentParam
                .builder().build();
        String json = objectMapper.writeValueAsString(registerStudentDto);

        Teacher teacher1 = Teacher.builder().email("teacher1@gmail.com").name("tea").build();

        when(this.teacherService.getTeacher(teacher1.getEmail())).thenReturn(teacher1);
        when(this.studentService.countStudentFromEmailList(registerStudentDto.getStudentEmailList()))
                .thenReturn(Long.valueOf("0"));

        when(this.teacherService.registerStudent(registerStudentDto)).thenReturn(true);

        this.mockMvc.perform(post("/register")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", hasItem("Teacher email can not be empty")))
                .andExpect(jsonPath("$.message", hasItem("Teacher email is not provided")))
                .andExpect(jsonPath("$.message", hasItem("Student email can not be empty")));
    }

    @Test()
    @DisplayName(value = "Should return 400 when valid teacher email and empty student email list")
    void testRegisterStudentsToTeacherReturnStatusCodeWhenFailure2() throws Exception {
        RegisterStudentParam registerStudentDto = RegisterStudentParam
                .builder().teacherEmail("teacher1@gmail.com").build();
        String json = objectMapper.writeValueAsString(registerStudentDto);

        Teacher teacher1 = Teacher.builder().email("teacher1@gmail.com").name("tea").build();

        when(this.teacherService.getTeacher(teacher1.getEmail())).thenReturn(teacher1);
        when(this.studentService.countStudentFromEmailList(registerStudentDto.getStudentEmailList()))
                .thenReturn(Long.valueOf("0"));
        when(this.teacherService.registerStudent(registerStudentDto)).thenReturn(true);

        this.mockMvc.perform(post("/register")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", hasItem("Student email can not be empty")));
    }

    @Test()
    @DisplayName(value = "Should return 200 and student list of 2 student when common student found")
    void testCommonStudentWhenSuccess() throws Exception {
        List<String> teacher = Arrays.asList("teacher1@gmail.com", "teacher2@gmail.com");

        Student student1 = Student.builder().email("student1@gmail.com").name("student1").build();
        Student student2 = Student.builder().email("student2@gmail.com").name("student2").build();
        List<Student> students = Arrays.asList(student1,student2);
        String json = objectMapper.writeValueAsString(teacher);

        when(this.teacherService.countTeacherFromEmailList(teacher)).thenReturn(2L);
        when(this.studentService.getCommonStudentByTeacherEmailList(teacher)).thenReturn(students);

        this.mockMvc.perform(get("/commonstudents")
                .param("teacher",teacher.get(0))
                .param("teacher",teacher.get(1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.students", is(notNullValue())))
                .andExpect(jsonPath("$.students", hasSize(2)))
                .andExpect(jsonPath("$.students", hasItem(student1.getEmail())))
                .andExpect(jsonPath("$.students", hasItem(student2.getEmail())));
    }

    @Test()
    @DisplayName(value = "Should return 200 and empty list when no common student found")
    void testCommonStudentWhenSuccess1() throws Exception {
        List<String> teacher = Arrays.asList("teacher1@gmail.com", "teacher2@gmail.com");
        String json = objectMapper.writeValueAsString(teacher);

        when(this.teacherService.countTeacherFromEmailList(teacher)).thenReturn(2L);
        when(this.studentService.getCommonStudentByTeacherEmailList(teacher)).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/commonstudents")
                        .param("teacher",teacher.get(0))
                        .param("teacher",teacher.get(1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.students", is(notNullValue())))
                .andExpect(jsonPath("$.students", hasSize(0)));
    }

    @Test()
    @DisplayName(value = "Should return 400 when one invalid teacher email and one valid teacher email params")
    void testCommonStudentWhenFailure1() throws Exception {
        List<String> teacher = Arrays.asList("teacher1gmail.com", "teacher2@gmail.com");

        Student student1 = Student.builder().email("student1@gmail.com").name("student1").build();
        Student student2 = Student.builder().email("student2@gmail.com").name("student2").build();
        List<Student> students = Arrays.asList(student1,student2);
        String json = objectMapper.writeValueAsString(teacher);

        when(this.teacherService.countTeacherFromEmailList(teacher)).thenReturn(2L);
        when(this.studentService.getCommonStudentByTeacherEmailList(teacher)).thenReturn(students);



        this.mockMvc.perform(get("/commonstudents")
                        .param("teacher",teacher.get(0))
                        .param("teacher",teacher.get(1)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", is(" must be a well-formed email address")));
    }


    @Test()
    @DisplayName(value = "Should return 400 when one invalid teacher email and one valid teacher email params")
    void testCommonStudentWhenFailure2() throws Exception {
        List<String> teacher = Arrays.asList("teacher1gmail.com", "teacher2@gmail.com");

        Student student1 = Student.builder().email("student1@gmail.com").name("student1").build();
        Student student2 = Student.builder().email("student2@gmail.com").name("student2").build();
        List<Student> students = Arrays.asList(student1,student2);
        String json = objectMapper.writeValueAsString(teacher);

        when(this.teacherService.countTeacherFromEmailList(teacher)).thenReturn(2L);
        when(this.studentService.getCommonStudentByTeacherEmailList(teacher)).thenReturn(students);

        this.mockMvc.perform(get("/commonstudents")
                        .param("teacher",teacher.get(0))
                        .param("teacher",teacher.get(1)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", is(" must be a well-formed email address")));
    }

    @Test()
    @DisplayName(value = "Should return 400 when one invalid unregistered teacher email and one valid registered teacher email params")
    void testCommonStudentWhenFailure3() throws Exception {
        List<String> teacher = Arrays.asList("teacher1@gmail.com", "teacher2@gmail.com");

        Student student1 = Student.builder().email("student1@gmail.com").name("student1").build();
        Student student2 = Student.builder().email("student2@gmail.com").name("student2").build();
        List<Student> students = Arrays.asList(student1,student2);
        String json = objectMapper.writeValueAsString(teacher);

        when(this.teacherService.countTeacherFromEmailList(teacher)).thenReturn(1L);
        when(this.studentService.getCommonStudentByTeacherEmailList(teacher)).thenReturn(students);

        this.mockMvc.perform(get("/commonstudents")
                        .param("teacher",teacher.get(0))
                        .param("teacher",teacher.get(1)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", is("Some teacher's email wasn't registered")));
    }
}