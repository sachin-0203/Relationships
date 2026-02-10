package com.phase3.Relationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phase3.Relationships.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

import com.phase3.Relationships.dto.response.DepartmentResponseDto;
import com.phase3.Relationships.dto.response.EmployeeResponseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Long>  {
  

  @Query("""
    SELECT new com.phase3.Relationships.dto.response.EmployeeResponseDto(
      e.id,
      e.name,
      e.email,
      e.salary,
      e.joiningDate,
      d.id
    ) 
    FROM EmployeeEntity e JOIN e.department d
  """)
  List<EmployeeResponseDto> findAllEmployeesProjected();
  
  @Query("""
    SELECT new com.phase3.Relationships.dto.response.EmployeeResponseDto(
      e.id,
      e.name,
      e.email,
      e.salary,
      e.joiningDate,
      d.id
    )
    FROM EmployeeEntity e JOIN e.department d 
    WHERE e.id = :id
  """)
  Optional<EmployeeResponseDto> findEmployeeByIdProjected( @Param("id") Long id);

  @Query("""
    SELECT new com.phase3.Relationships.dto.response.DepartmentResponseDto(
      d.id,
      d.name,
      d.code,
      d.description
    )
    FROM EmployeeEntity e JOIN e.department d WHERE e.id = :empId    
  """)
  Optional<DepartmentResponseDto> findDepartmentByEmployeeIdProjected(@Param("empId") Long id);

}
