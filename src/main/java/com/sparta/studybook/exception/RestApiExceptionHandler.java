package com.sparta.studybook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<ResponseDto> handleApiRequestException(IllegalArgumentException ex) {

        return ResponseEntity.badRequest().body(new ResponseDto<>(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null));

    }

}