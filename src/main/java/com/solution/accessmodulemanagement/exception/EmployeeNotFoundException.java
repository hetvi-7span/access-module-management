package com.solution.accessmodulemanagement.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
