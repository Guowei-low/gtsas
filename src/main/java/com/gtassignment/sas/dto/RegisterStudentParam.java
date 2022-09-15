package com.gtassignment.sas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStudentParam {
    @JsonProperty("teacher")
    @NotBlank(message = "Teacher email is not provided")
    @NotNull(message = "Teacher email can not be empty")
    @Email(message = "Email is not valid")
    private String teacherEmail;

    @JsonProperty("students")
    @NotNull(message = "Student email can not be empty")
    private List<@Email String> studentEmailList = new ArrayList<>();
}
