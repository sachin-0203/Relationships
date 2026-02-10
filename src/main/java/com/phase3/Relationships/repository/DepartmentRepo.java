package com.phase3.Relationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phase3.Relationships.dto.response.DepartmentResponseDto;
import com.phase3.Relationships.entity.DepartmentEntity;
import java.util.List;
import java.util.Optional;



public interface DepartmentRepo extends JpaRepository<DepartmentEntity,Long> {

  @Query("""
    SELECT new com.phase3.Relationships.dto.response.DepartmentResponseDto(
      d.id,
      d.name,
      d.code,
      d.description
    ) FROM DepartmentEntity d   
  """)
  List<DepartmentResponseDto> findAllDepartmentProjected();

  @Query("""
    SELECT new com.phase3.Relationships.dto.response.DepartmentResponseDto(
      d.id,
      d.name,
      d.code,
      d.description
    ) FROM DepartmentEntity d WHERE d.id = :id 
  """)
  Optional<DepartmentResponseDto> findDepartmentByIdProjected(@Param("id") Long id);
}
