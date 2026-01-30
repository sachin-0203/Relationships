package com.phase3.Relationships.service;

import java.util.List;

import com.phase3.Relationships.dto.response.DepartmentResponseDto;
import com.phase3.Relationships.dto.response.EmployeeResponseDto;
import com.phase3.Relationships.dto.request.DepartmentCreateRequestDto;
import com.phase3.Relationships.dto.request.DepartmentUpdateRequestDto;


public interface DepartmentService {
  
  public DepartmentResponseDto createDepartment(DepartmentCreateRequestDto dto);

  public DepartmentResponseDto updateDepartment(Long id, DepartmentUpdateRequestDto dto);
  
  public boolean deleteDepartment(Long id);

  public List<EmployeeResponseDto> getDepartmentEmployee(Long depId);
  
}
