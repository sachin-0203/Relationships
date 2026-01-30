package com.phase3.Relationships.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;



@Entity
@Getter
@Setter
public class EmployeeEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private BigDecimal salary;
  private LocalDate joiningDate;

  @ManyToOne
  @JoinColumn(name = "dept_id")
  private DepartmentEntity department;
  
}
