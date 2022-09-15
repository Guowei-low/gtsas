package com.gtassignment.sas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherParam {
    @NotNull(message = "Teacher name is not provided")
    @NotBlank(message = "Teacher email can not be empty")
    private String name;

    @NotNull(message = "Teacher email is not provided")
    @NotBlank(message = "Teacher email can not be empty")
    @Email(message = "Email is not valid")
    private String email;
}
