package com.solution.accessmodulemanagement.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Data
public class ResponseDto {

    String message;
    HttpStatus httpStatus;

    HttpStatusCode httpStatusCode;
    Object data;
}
