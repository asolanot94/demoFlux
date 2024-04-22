package com.solano.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleBookAPIException(ValidationException validationException){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error message", validationException.getMessage());
        errorMap.put("status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }
}
