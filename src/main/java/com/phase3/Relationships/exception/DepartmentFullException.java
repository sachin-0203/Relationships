package com.phase3.Relationships.exception;

public class DepartmentFullException extends RuntimeException {
    public DepartmentFullException(String message){
        super(message);
    }
}
