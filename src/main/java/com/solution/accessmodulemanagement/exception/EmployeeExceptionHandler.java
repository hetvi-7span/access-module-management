package com.solution.accessmodulemanagement.exception;

import com.solution.accessmodulemanagement.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice

public class EmployeeExceptionHandler {
    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<ResponseDto> exception(EmployeeNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto("Employee not found",HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> defaultExceptionHandler(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(new ResponseDto(exception.getMessage(),HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmployeeException.class)
    public ResponseEntity<ResponseDto> empException(EmployeeException exception) {
        return new ResponseEntity<>(new ResponseDto(exception.getErrorMessage(),exception.getHttpStatus(),exception.getHttpStatusCode()), exception.getHttpStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> requestException(MethodArgumentNotValidException exception) {
        List<String> errorMessages = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));
        return new ResponseEntity<>(new ResponseDto(errorMessages,HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
