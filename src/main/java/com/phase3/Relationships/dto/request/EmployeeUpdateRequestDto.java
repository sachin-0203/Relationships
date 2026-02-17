package com.phase3.Relationships.dto.request;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;

@Data
public class EmployeeUpdateRequestDto {
  
  private Long id;

  private String name;
  
  @Email
  private String email;
  
  private BigDecimal salary;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @PastOrPresent(message = "joining date cannot be a future date")
  private LocalDate joiningDate;

}
