package com.phase3.Relationships.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDto {

  private Long id;
  private String name;
  private String code;
  private String description;
  private Integer maxCapacity;
  
}