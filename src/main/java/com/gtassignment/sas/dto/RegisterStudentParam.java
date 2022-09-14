package com.gtassignment.sas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStudentParam {
    @JsonProperty("teacher")
    private String teacherEmail;
    @JsonProperty("students")
    private List<String> studentEmailList = new ArrayList<>();
}
