package com.gtassignment.sas.controller;

import com.gtassignment.sas.exeption.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    @ExceptionHandler({ ErrorResponse.class })
    public ResponseEntity<String> handleException(ErrorResponse err) {
        return ResponseEntity.status(err.getHttpStatus()).body(err.getMessage());
    }
}
