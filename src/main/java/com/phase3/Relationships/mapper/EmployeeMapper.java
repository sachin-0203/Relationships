package com.phase3.Relationships.mapper;

import org.springframework.stereotype.Component;

import com.phase3.Relationships.dto.request.EmployeeCreateRequestDto;
import com.phase3.Relationships.dto.request.EmployeeUpdateRequestDto;
import com.phase3.Relationships.dto.response.EmployeeResponseDto;
import com.phase3.Relationships.entity.EmployeeEntity;


@Component
public class EmployeeMapper {

  public EmployeeEntity fromCreateRequest(EmployeeCreateRequestDto dto){
    EmployeeEntity newEntity = new EmployeeEntity();
    newEntity.setName(dto.getName());
    newEntity.setEmail(dto.getEmail());
    newEntity.setJoiningDate(dto.getJoiningDate());
    newEntity.setSalary(dto.getSalary());
    return newEntity;
  }

  public void updateEntityFromRequest(EmployeeUpdateRequestDto dto, EmployeeEntity entity){
    if(dto.getName() != null){
      entity.setName(dto.getName());
    }
    if(dto.getEmail() != null){
      entity.setEmail(dto.getEmail());
    }
    if(dto.getJoiningDate() != null){
      entity.setJoiningDate(dto.getJoiningDate());
    }
    if(dto.getSalary() != null){
      entity.setSalary(dto.getSalary());
    }
  }

  public EmployeeResponseDto toResponseDto(EmployeeEntity entity){
    EmployeeResponseDto response = new EmployeeResponseDto();
    response.setId(entity.getId());
    response.setName(entity.getName());
    response.setEmail(entity.getEmail());
    response.setJoiningDate(entity.getJoiningDate());
    response.setSalary(entity.getSalary());
    if(entity.getDepartment() != null){
      response.setDepartmentId(entity.getDepartment().getId());
    }
    return response;
  }

}
