package com.phase3.Relationships.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phase3.Relationships.dto.request.DepartmentCreateRequestDto;
import com.phase3.Relationships.dto.request.DepartmentUpdateRequestDto;
import com.phase3.Relationships.dto.response.DepartmentResponseDto;
import com.phase3.Relationships.dto.response.EmployeeResponseDto;
import com.phase3.Relationships.service.Impl.DepartmentServiceImplementation;

@RestController
@RequestMapping("/department")
public class DepartmentController {
  
  private final DepartmentServiceImplementation departmentServices;

  public DepartmentController(DepartmentServiceImplementation departmentServices){
    this.departmentServices = departmentServices;
  }


  
  @GetMapping
  public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments(){
    return ResponseEntity.ok(departmentServices.getAllDepartments());
  }



  @PostMapping
  public ResponseEntity<DepartmentResponseDto> createDepartment(@RequestBody DepartmentCreateRequestDto dto){
    return ResponseEntity.ok(departmentServices.createDepartment(dto));
  }
  


  @GetMapping("/{id}")
  public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Long id){
    return ResponseEntity.ok(departmentServices.getDepartmentById(id));
  }



  @PutMapping("/{id}")
  public ResponseEntity<DepartmentResponseDto> updateDepartment(@PathVariable Long id , @RequestBody DepartmentUpdateRequestDto dto){
    return ResponseEntity.ok(departmentServices.updateDepartment(id, dto));
  }



  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long id){
    return ResponseEntity.ok(departmentServices.deleteDepartment(id));
  }



  @GetMapping("/{depId}/employee")
  public ResponseEntity<List<EmployeeResponseDto>> getDepartmentEmployees(@PathVariable Long depId){
    return ResponseEntity.ok(departmentServices.getDepartmentEmployee(depId));
  }


}
