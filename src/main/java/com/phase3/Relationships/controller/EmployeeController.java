package com.phase3.Relationships.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phase3.Relationships.dto.request.EmployeeCreateRequestDto;
import com.phase3.Relationships.dto.request.EmployeeUpdateRequestDto;
import com.phase3.Relationships.dto.response.DepartmentResponseDto;
import com.phase3.Relationships.dto.response.EmployeeResponseDto;
import com.phase3.Relationships.service.Impl.EmployeeServicesImplementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/employee")
public class EmployeeController {
  
  private final EmployeeServicesImplementation employeeServices;

  public EmployeeController(EmployeeServicesImplementation employeeServices){
    this.employeeServices = employeeServices;
  }


  @GetMapping
  public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees(){
    return ResponseEntity.ok(employeeServices.getAllEmployees());
  }

  @PostMapping
  public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeCreateRequestDto dto){
    return ResponseEntity.ok(employeeServices.createEmployee(dto));
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id){
    return ResponseEntity.ok(employeeServices.getEmployeeById(id));
  }

  @PutMapping("{id}")
  public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateRequestDto dto) {
      return ResponseEntity.ok(employeeServices.updateEmployee(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id){
    return ResponseEntity.ok(employeeServices.deleteEmployee(id));
  }

  @GetMapping("/{empId}/department")
  public ResponseEntity<DepartmentResponseDto> getEmployeeDepartment(@PathVariable Long empId){
    return ResponseEntity.ok(employeeServices.getEmployeeDepartment(empId));
  }

  @PutMapping("/{empId}/department/{depId}")
  public ResponseEntity<EmployeeResponseDto> assingEmployeeDepartment(@PathVariable Long empId , @PathVariable Long depId){
    return ResponseEntity.ok(employeeServices.assingEmployeeDepartment(empId, depId));
  }

}
