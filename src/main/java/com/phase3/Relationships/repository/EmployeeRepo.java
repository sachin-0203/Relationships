package com.phase3.Relationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phase3.Relationships.entity.EmployeeEntity;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Long>  {
  
}
