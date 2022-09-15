package com.gtassignment.sas.controller;

import com.gtassignment.sas.exeption.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

public class BaseController {

    @ExceptionHandler({ ErrorResponse.class })
    public ResponseEntity<Object> handleException(ErrorResponse err) {
        return ResponseEntity.status(err.getHttpStatus())
                .body(Collections.singletonMap("message", err.getMessage()));
    }

    //Uniqueness violated
    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleInvalidParamException(Exception err) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections
                        .singletonMap("mesaage", "Existing data found in database, data cannot be duplicated"));
    }

//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<Object> handleException(Exception err) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(Collections
//                        .singletonMap("mesaage", "Unknown Error, please contact admin"));
//    }

}
