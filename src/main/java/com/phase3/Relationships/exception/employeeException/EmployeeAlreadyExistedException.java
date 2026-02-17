package com.phase3.Relationships.exception.employeeException;

public class EmployeeAlreadyExistedException extends RuntimeException {
    public EmployeeAlreadyExistedException(String message){
        super(message);
    }
}
