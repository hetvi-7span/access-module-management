package com.solution.accessmodulemanagement.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Getter
@Setter
public class ResponseDto {

    String message;
    HttpStatus httpStatus;

    Integer httpStatusCode;

    public ResponseDto(String message, HttpStatus httpStatus, Integer httpStatusCode) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatusCode;
    }

    public ResponseDto() {}
}
