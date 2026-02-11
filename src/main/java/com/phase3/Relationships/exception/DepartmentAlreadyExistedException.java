package com.phase3.Relationships.exception;

public class DepartmentAlreadyExistedException extends RuntimeException {
    public DepartmentAlreadyExistedException(String message){
        super(message);
    }
}
