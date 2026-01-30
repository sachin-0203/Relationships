package com.phase3.Relationships.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class EmployeeResponseDto {

  private Long Id;

  private String name;
  
  private String email;
  
  private BigDecimal salary;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate joiningDate;

  private Long departmentId; 

}