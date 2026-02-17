package com.phase3.Relationships.service.Impl;

import java.util.List;

import com.phase3.Relationships.exception.departmentException.DepartmentFullException;
import com.phase3.Relationships.exception.ValueRequiredException;
import com.phase3.Relationships.exception.employeeException.EmployeeAlreadyExistedException;
import org.springframework.stereotype.Service;

import com.phase3.Relationships.dto.request.EmployeeCreateRequestDto;
import com.phase3.Relationships.dto.request.EmployeeUpdateRequestDto;
import com.phase3.Relationships.dto.response.DepartmentResponseDto;
import com.phase3.Relationships.dto.response.EmployeeResponseDto;
import com.phase3.Relationships.entity.DepartmentEntity;
import com.phase3.Relationships.entity.EmployeeEntity;
import com.phase3.Relationships.mapper.EmployeeMapper;
import com.phase3.Relationships.repository.DepartmentRepo;
import com.phase3.Relationships.repository.EmployeeRepo;
import com.phase3.Relationships.service.EmployeeService;

import org.springframework.transaction.annotation.Transactional;
import com.phase3.Relationships.exception.ResourceNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@Service
public class EmployeeServicesImplementation implements EmployeeService {
  
  private final EmployeeRepo employeeRepo;
  private final DepartmentRepo departmentRepo;
  private final EmployeeMapper employeeMapper;

  public EmployeeServicesImplementation(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo,EmployeeMapper employeeMapper){
    this.employeeRepo = employeeRepo;
    this.departmentRepo = departmentRepo;
    this.employeeMapper = employeeMapper;
  }


  @Override
  public List<EmployeeResponseDto> getAllEmployees(){
    return employeeRepo.findAllEmployeesProjected();
  }
  


  @Override
  public EmployeeResponseDto getEmployeeById(Long id){
    return employeeRepo.findEmployeeByIdProjected(id)
    .orElseThrow(() -> new ResourceNotFoundException(
      "Employee not found with id: " + id
    ));
  }
  


  @Override
  @Transactional
  public EmployeeResponseDto hireEmployee(EmployeeCreateRequestDto dto){

    if(dto.getDepartmentId() == null){
      throw new ValueRequiredException("Department is required!");
    }

    DepartmentEntity department = departmentRepo.findById(dto.getDepartmentId()).orElseThrow(
      ()-> new ResourceNotFoundException("Department not found")
    );

    long currentSize = employeeRepo.countByDepartmentId(department.getId());
    if(currentSize >= department.getMaxCapacity()){
      throw new DepartmentFullException("Department is full!");
    }

    if(employeeRepo.existsByEmail(dto.getEmail())){
      throw new EmployeeAlreadyExistedException("Employee is already existed with the email: " + dto.getEmail());
    }

    EmployeeEntity employee = employeeMapper.fromCreateRequest(dto);
    employee.setDepartment(department);
    
    EmployeeEntity saved = employeeRepo.save(employee);
    return employeeMapper.toResponseDto(saved);
  
  }
  


  @Override
  @Transactional
  public EmployeeResponseDto updateEmployee(Long id, EmployeeUpdateRequestDto dto){

    EmployeeEntity existing = employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: " + id) );
    employeeMapper.updateEntityFromRequest(dto, existing);
    return employeeMapper.toResponseDto(existing);
    
  }
  


  @Override
  public boolean terminateEmployee(Long id){
    if(!employeeRepo.existsById(id)) return false;
    employeeRepo.deleteById(id);
    return true;
  }
  


  @Override
  @Transactional
  public EmployeeResponseDto transferEmployee(Long empId, Long depId){
    
    EmployeeEntity emp = employeeRepo.findById(empId).orElseThrow( ()-> new ResourceNotFoundException("Employee not found with id: " + empId));

    DepartmentEntity dep = departmentRepo.findById(depId).orElseThrow(()-> new ResourceNotFoundException("Department not found with id: " + depId));

    if(employeeRepo.countByDepartmentId(depId) >= dep.getMaxCapacity()){
      throw new DepartmentFullException("department is full! so cannot transfer employee to department with id: " + depId);
    }
    emp.setDepartment(dep);

    return employeeMapper.toResponseDto(emp);

  }
  


  @Override
  @Transactional(readOnly = true)
  public DepartmentResponseDto getEmployeeDepartment(Long empId){
    return employeeRepo.findDepartmentByEmployeeIdProjected(empId).orElseThrow( ()-> new ResourceNotFoundException("Employee not found with id: " + empId));
  }

  @Transactional(readOnly = true)
  public Page<EmployeeResponseDto> getEmployees(Pageable pageable){
    Page<EmployeeEntity> pageEntities = employeeRepo.findAll(pageable);
    return pageEntities.map(employeeMapper::toResponseDto);
  }
}
