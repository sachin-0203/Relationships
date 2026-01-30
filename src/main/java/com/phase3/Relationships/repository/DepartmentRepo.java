package com.phase3.Relationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phase3.Relationships.entity.DepartmentEntity;
import java.util.List;
import java.util.Optional;



public interface DepartmentRepo extends JpaRepository<DepartmentEntity,Long> {
  
  Optional<DepartmentEntity> findById(Long id);
  List<DepartmentEntity> findAll();
}
