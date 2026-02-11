package com.phase3.Relationships.dto.request;

import lombok.Data;


@Data
public class DepartmentUpdateRequestDto {

  private Long id;
  private String name;
  private String code;
  private String description;
  private Integer maxCapacity;

}
