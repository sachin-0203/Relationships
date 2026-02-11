package com.phase3.Relationships.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phase3.Relationships.dto.request.DepartmentCreateRequestDto;
import com.phase3.Relationships.dto.request.DepartmentUpdateRequestDto;
import com.phase3.Relationships.dto.response.DepartmentResponseDto;
import com.phase3.Relationships.dto.response.EmployeeResponseDto;
import com.phase3.Relationships.entity.DepartmentEntity;
import com.phase3.Relationships.entity.EmployeeEntity;
import com.phase3.Relationships.mapper.DepartmentMapper;
import com.phase3.Relationships.mapper.EmployeeMapper;
import com.phase3.Relationships.repository.DepartmentRepo;
import com.phase3.Relationships.service.DepartmentService;
import com.phase3.Relationships.exception.ResourceNotFoundException;


@Service
public class DepartmentServiceImplementation implements DepartmentService  {
  
  private final DepartmentRepo departmentRepo;
  private final DepartmentMapper departmentMapper;

  public DepartmentServiceImplementation(DepartmentRepo departmentRepo, DepartmentMapper departmentMapper){
    this.departmentRepo = departmentRepo;
    this.departmentMapper = departmentMapper;
  }

  @Override
  @Transactional
  public DepartmentResponseDto createDepartment(DepartmentCreateRequestDto dto){

    DepartmentEntity newDep = departmentMapper.fromCreateRequest(dto);
    DepartmentEntity saved  = departmentRepo.save(newDep);
    DepartmentResponseDto response = departmentMapper.toResponseDto(saved);
    return response;
  }

  @Override
  @Transactional
  public DepartmentResponseDto updateDepartment(Long id, DepartmentUpdateRequestDto dto){
    
    DepartmentEntity exitsting = departmentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department is not found with id: " + id));

    departmentMapper.updateEntityfromRequest(dto, exitsting);
    return departmentMapper.toResponseDto(exitsting);
  }

  @Override
  public boolean deleteDepartment(Long id){

    if(!departmentRepo.existsById(id)){
      return false;
    }

    departmentRepo.deleteById(id);
    return true;
  }

  @Override
  @Transactional(readOnly = true)
  public List<EmployeeResponseDto> getDepartmentEmployee(Long depId){
    return departmentRepo.getDepartmentEmployeesProjected(depId);
  }

  @Transactional(readOnly = true)
  public List<DepartmentResponseDto> getAllDepartments(){
    return departmentRepo.findAllDepartmentProjected();
  }

  @Transactional(readOnly = true)
  public DepartmentResponseDto getDepartmentById(Long id){
    return departmentRepo.findDepartmentByIdProjected(id).orElseThrow( ()-> new ResourceNotFoundException("Department not found with id: " + id));
  }
}
