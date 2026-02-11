package com.phase3.Relationships.dto.request;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


@Data
public class EmployeeCreateRequestDto {

  @NotBlank(message = "name can't be empty")
  private String name;
  
  @Email(message = "please enter a valid email")
  private String email;
  
  @NotNull(message = "please enter salary")
  private BigDecimal salary;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @PastOrPresent(message = "please provide the past or present date")
  private LocalDate joiningDate;

  @NotNull(message =  "please provide the department Id")
  private Long departmentId; 
  
}
