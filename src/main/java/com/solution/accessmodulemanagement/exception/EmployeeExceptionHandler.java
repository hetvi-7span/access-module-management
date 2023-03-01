package com.solution.accessmodulemanagement.exception;

import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class EmployeeExceptionHandler {
    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<ResponseDto> exception(EmployeeNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto("Employee not found",HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
}
