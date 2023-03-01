package com.solution.accessmodulemanagement.dto.response;

import org.springframework.http.HttpStatus;

public class ExcpetionResponseDto extends ResponseDto{
    public ExcpetionResponseDto(String messages, HttpStatus status, Integer code) {
        super(messages,status,code);
    }
}
