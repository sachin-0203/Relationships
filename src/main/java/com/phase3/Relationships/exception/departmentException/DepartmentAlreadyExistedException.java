package com.phase3.Relationships.exception.departmentException;

public class DepartmentAlreadyExistedException extends RuntimeException {
    public DepartmentAlreadyExistedException(String message){
        super(message);
    }
}
