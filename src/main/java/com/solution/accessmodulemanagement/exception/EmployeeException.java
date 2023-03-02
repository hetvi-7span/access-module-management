package com.solution.accessmodulemanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class EmployeeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Integer httpStatusCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    public EmployeeException(String errorMessage,Integer httpStatusCode, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
    }



}
