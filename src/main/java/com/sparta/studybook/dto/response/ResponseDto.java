package com.sparta.studybook.dto.response;

import lombok.Getter;

@Getter
public class ResponseDto {

    private int status;
    private String message;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
