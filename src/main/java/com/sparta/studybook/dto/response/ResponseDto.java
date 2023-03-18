package com.sparta.studybook.dto.response;

import lombok.Getter;

@Getter
public class ResponseDto<T> {
    private final String msg;
    private final int statusCode;
    private final T data;

    public ResponseDto(String msg, int statusCode, T data) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.data = data;
    }
}