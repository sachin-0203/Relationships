package com.phase3.Relationships.service;

import java.util.List;

import com.phase3.Relationships.dto.request.EmployeeCreateRequestDto;
import com.phase3.Relationships.dto.request.EmployeeUpdateRequestDto;
import com.phase3.Relationships.dto.response.EmployeeResponseDto;
import com.phase3.Relationships.dto.response.DepartmentResponseDto;

public interface EmployeeService {

  public List<EmployeeResponseDto> getAllEmployees();
  public EmployeeResponseDto getEmployeeById(Long id);
  public EmployeeResponseDto hireEmployee(EmployeeCreateRequestDto dto);
  public EmployeeResponseDto updateEmployee(Long id, EmployeeUpdateRequestDto dto);
  public boolean deleteEmployee(Long id);

  public EmployeeResponseDto transferEmployee(Long empId, Long depId);
  public DepartmentResponseDto getEmployeeDepartment(Long empId);

}
