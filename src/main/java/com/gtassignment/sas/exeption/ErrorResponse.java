package com.gtassignment.sas.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse extends Exception {
    private String message;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
