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
public class StudentParam {
    @NotNull(message = "Student name is not provided")
    @NotBlank(message = "Student email can not be empty")
    private String name;

    @NotNull(message = "Student email is not provided")
    @NotBlank(message = "Student email can not be empty")
    @Email(message = "Email is not valid")
    private String email;
}
