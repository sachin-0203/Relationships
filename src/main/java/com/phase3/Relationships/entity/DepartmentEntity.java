package com.phase3.Relationships.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;



@Entity
@Getter
@Setter
public class DepartmentEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String name;
  private String code;
  private String description;

  @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
  private List<EmployeeEntity> employeesList = new ArrayList<>();

}
