package com.phase3.Relationships.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class DepartmentCreateRequestDto {

  @NotBlank(message = "enter department name")
  private String name;

  @NotBlank(message = "enter department code")
  private String code;
  
  @NotBlank(message = "enter department description")
  private String description;

}
