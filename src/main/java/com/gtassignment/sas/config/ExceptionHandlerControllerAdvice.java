package com.gtassignment.sas.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

        @ExceptionHandler({Exception.class})
        public ResponseEntity<Object> handleInvalidParamException(Exception err) {
                String errorMessage = "";

                if (err.getClass().equals(ConstraintViolationException.class)) {
                        if (err.getMessage().indexOf(":") >= 0) {
                                errorMessage = err.getMessage().split(":")[1];
                        } else {
                                errorMessage = "Constraint violation";
                        }
                } else {
                        errorMessage = "Server error " + err.getClass();
                }
                return ResponseEntity.status(BAD_REQUEST)
                        .body(Collections.singletonMap("message", errorMessage));
        }

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
                final List<String> details = new ArrayList<>();
                for (final ObjectError error : ex.getBindingResult().getAllErrors()) {
                        details.add(error.getDefaultMessage());
                }
                return new ResponseEntity(Collections.singletonMap("message", details), HttpStatus.BAD_REQUEST);
        }
}