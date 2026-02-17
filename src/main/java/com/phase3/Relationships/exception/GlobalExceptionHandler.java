package com.phase3.Relationships.exception;

import java.time.LocalDateTime;

import com.phase3.Relationships.exception.departmentException.DepartmentAlreadyExistedException;
import com.phase3.Relationships.exception.departmentException.DepartmentDeleteException;
import com.phase3.Relationships.exception.departmentException.DepartmentFullException;
import com.phase3.Relationships.exception.employeeException.EmployeeAlreadyExistedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phase3.Relationships.dto.ExceptionDto;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ExceptionDto> ResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest req){

    ExceptionDto error = new ExceptionDto(
      LocalDateTime.now(),
      HttpStatus.NOT_FOUND.value(),
      HttpStatus.NOT_FOUND.name(),
      ex.getMessage(),
      req.getRequestURI()
    );

    return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionDto> handleValidException(MethodArgumentNotValidException ex, HttpServletRequest req){

    String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

    ExceptionDto validateError = new ExceptionDto(
      LocalDateTime.now(),
      HttpStatus.BAD_REQUEST.value(),
      HttpStatus.BAD_REQUEST.name(),
      message,
      req.getRequestURI()
    );

    return new ResponseEntity<>(validateError,HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DepartmentFullException.class)
  public ResponseEntity<ExceptionDto> handlerDepartmentFullException(DepartmentFullException ex, HttpServletRequest req){
    ExceptionDto storageError = new ExceptionDto(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.name(),
            ex.getMessage(),
            req.getRequestURI()
    );

    return new ResponseEntity<>(storageError,HttpStatus.CONFLICT);
  }

  @ExceptionHandler(DepartmentDeleteException.class)
  public ResponseEntity<ExceptionDto> handlerDepartmentDeleteException(DepartmentDeleteException ex, HttpServletRequest req){
    ExceptionDto storageError = new ExceptionDto(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.name(),
            ex.getMessage(),
            req.getRequestURI()
    );

    return new ResponseEntity<>(storageError,HttpStatus.CONFLICT);
  }

  @ExceptionHandler(DepartmentAlreadyExistedException.class)
  public ResponseEntity<ExceptionDto> handleDepartmentExistedException(DepartmentAlreadyExistedException ex, HttpServletRequest req){
    ExceptionDto existedError = new ExceptionDto(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.name(),
            ex.getMessage(),
            req.getRequestURI()
    );

    return new ResponseEntity<>(existedError,HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ValueRequiredException.class)
  public ResponseEntity<ExceptionDto> handleValueRequiredException(ValueRequiredException ex, HttpServletRequest req){
    ExceptionDto existedError = new ExceptionDto(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.name(),
            ex.getMessage(),
            req.getRequestURI()
    );

    return new ResponseEntity<>(existedError,HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EmployeeAlreadyExistedException.class)
  public ResponseEntity<ExceptionDto> handleEmployeeExistedException(EmployeeAlreadyExistedException ex, HttpServletRequest req){
    ExceptionDto existedError = new ExceptionDto(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.name(),
            ex.getMessage(),
            req.getRequestURI()
    );

    return new ResponseEntity<>(existedError,HttpStatus.CONFLICT);
  }

}
