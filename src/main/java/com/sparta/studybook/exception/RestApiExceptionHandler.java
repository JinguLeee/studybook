package com.sparta.studybook.exception;

import com.sparta.studybook.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<ResponseDto> handleApiRequestException(IllegalArgumentException ex) {


        return ResponseEntity.badRequest().body(new ResponseDto<>(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null));


        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto<>(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null));

    }

}