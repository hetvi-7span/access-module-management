package com.solution.accessmodulemanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {

    String message;
    HttpStatus httpStatus;
    Integer httpStatusCode;

    Object data;

    public ResponseDto(String message, HttpStatus httpStatus, Integer httpStatusCode) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatusCode;
    }

    public ResponseDto() {}
}
