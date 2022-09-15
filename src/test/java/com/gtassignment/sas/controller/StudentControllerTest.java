package com.gtassignment.sas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtassignment.sas.dto.StudentParam;
import com.gtassignment.sas.dto.SuspendStudentParam;
import com.gtassignment.sas.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test()
    @DisplayName(value = "Shoud return 201 when valid student data posted")
    void testSaveStudentReturnStatusCodeWhenSuccess() throws Exception {

        //given
        StudentParam studentDto = StudentParam
                .builder().email("student1@gmail.com").name("student1").build();

        String json = objectMapper.writeValueAsString(studentDto);
        when(this.studentService.saveStudent(studentDto)).thenReturn(0L);

        this.mockMvc.perform(post("/students")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test()
    @DisplayName(value = "Should return 400 when invalid student's email posted")
    void testSaveTeacherReturnStatusCodeWhenFailed() throws Exception {
        //given
        StudentParam studentDto = StudentParam
                .builder().email("student1gmail.com").name("student1").build();
        String json = objectMapper.writeValueAsString(studentDto);
        when(this.studentService.saveStudent(studentDto)).thenReturn(0L);

        this.mockMvc.perform(post("/students")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", hasItem("Email is not valid")));
    }

    @Test()
    @DisplayName(value = "Should return 201 when valid registered student's email posted")
    void testSuspendStudentWhenSuccess() throws Exception {
        //given
        SuspendStudentParam suspendStudentDto = SuspendStudentParam
                .builder().student("student1@gmail.com").build();
        String json = objectMapper.writeValueAsString(suspendStudentDto);

        this.mockMvc.perform(post("/suspend")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test()
    @DisplayName(value = "Should return 400 when invalid student's email posted")
    void testSuspendStudentWhenFailure() throws Exception {
        //given
        SuspendStudentParam suspendStudentDto = SuspendStudentParam
                .builder().student("student1gmail.com").build();
        String json = objectMapper.writeValueAsString(suspendStudentDto);

        this.mockMvc.perform(post("/suspend")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", hasItem("Email is not valid")));
    }

    @Test()
    @DisplayName(value = "Should return 400 when valid unregistered student's email posted")
    void testSuspendStudentWhenFailure1() throws Exception {
        //given
        SuspendStudentParam suspendStudentDto = SuspendStudentParam
                .builder().student("student1@gmail.com").build();
        String json = objectMapper.writeValueAsString(suspendStudentDto);

        doThrow(Exception.class).when(this.studentService).suspend(suspendStudentDto);

        this.mockMvc.perform(post("/suspend")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(notNullValue())))
                .andExpect(jsonPath("$.message", is("Student email is not registered")));
    }
}
