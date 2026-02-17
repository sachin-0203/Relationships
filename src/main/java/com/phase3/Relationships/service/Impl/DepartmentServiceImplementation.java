package com.phase3.Relationships.service.Impl;

import java.util.List;

import com.phase3.Relationships.exception.departmentException.DepartmentAlreadyExistedException;
import com.phase3.Relationships.exception.departmentException.DepartmentDeleteException;
import com.phase3.Relationships.repository.EmployeeRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phase3.Relationships.dto.request.DepartmentCreateRequestDto;
import com.phase3.Relationships.dto.request.DepartmentUpdateRequestDto;
import com.phase3.Relationships.dto.response.DepartmentResponseDto;
import com.phase3.Relationships.dto.response.EmployeeResponseDto;
import com.phase3.Relationships.entity.DepartmentEntity;
import com.phase3.Relationships.mapper.DepartmentMapper;
import com.phase3.Relationships.repository.DepartmentRepo;
import com.phase3.Relationships.service.DepartmentService;
import com.phase3.Relationships.exception.ResourceNotFoundException;


@Service
public class DepartmentServiceImplementation implements DepartmentService  {
  
  private final DepartmentRepo departmentRepo;
  private final DepartmentMapper departmentMapper;
  private final EmployeeRepo employeeRepo;

  public DepartmentServiceImplementation(DepartmentRepo departmentRepo, DepartmentMapper departmentMapper, EmployeeRepo employeeRepo){
    this.departmentRepo = departmentRepo;
    this.departmentMapper = departmentMapper;
    this.employeeRepo = employeeRepo;
  }

  @Override
  @Transactional
  public DepartmentResponseDto createDepartment(DepartmentCreateRequestDto dto){

    if(departmentRepo.existsByCode(dto.getCode())){
      throw new DepartmentAlreadyExistedException("This Department id already existed! with code : " + dto.getCode());
    }
    DepartmentEntity newDep = departmentMapper.fromCreateRequest(dto);
    DepartmentEntity saved  = departmentRepo.save(newDep);
    return departmentMapper.toResponseDto(saved);
  }

  @Override
  @Transactional
  public DepartmentResponseDto updateDepartment(Long id, DepartmentUpdateRequestDto dto){
    
    DepartmentEntity existing = departmentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department is not found with id: " + id));

    departmentMapper.updateEntityfromRequest(dto, existing);
    return departmentMapper.toResponseDto(existing);
  }

  @Override
  @Transactional
  public boolean deleteDepartment(Long id){

    DepartmentEntity existingDep = departmentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not exist with id : " + id));
    long employeeCount = employeeRepo.countByDepartmentId(id);
    if(employeeCount > 0){
      throw new DepartmentDeleteException("Cannot delete department with existing employees");
    }
    departmentRepo.deleteById(existingDep.getId());
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
