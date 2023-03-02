package com.solution.accessmodulemanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    String message;
    HttpStatus httpStatus;
    Integer httpStatusCode;
    Object data;
    List<String> errorMessage;

    public ResponseDto(String message, HttpStatus httpStatus, Integer httpStatusCode) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatusCode;
    }

    public ResponseDto(List<String> errorMessage,HttpStatus httpStatus, Integer httpStatusCode) {
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatusCode;
        this.errorMessage = errorMessage;
    }

    public ResponseDto() {}
}
