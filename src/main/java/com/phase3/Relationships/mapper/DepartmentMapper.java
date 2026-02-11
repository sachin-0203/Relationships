package com.phase3.Relationships.mapper;

import org.springframework.stereotype.Component;

import com.phase3.Relationships.dto.request.DepartmentCreateRequestDto;
import com.phase3.Relationships.dto.request.DepartmentUpdateRequestDto;
import com.phase3.Relationships.dto.response.DepartmentResponseDto;
import com.phase3.Relationships.entity.DepartmentEntity;


@Component
public class DepartmentMapper {

  public DepartmentEntity fromCreateRequest(DepartmentCreateRequestDto dto){
   
    DepartmentEntity newEntity = new DepartmentEntity();
    newEntity.setName(dto.getName());
    newEntity.setCode(dto.getCode());
    newEntity.setDescription(dto.getDescription());
    newEntity.setMaxCapacity(dto.getMaxCapacity());
    return newEntity;
  }

  public void updateEntityfromRequest(DepartmentUpdateRequestDto dto, DepartmentEntity entity){

    if(dto.getName() != null){
      entity.setName(dto.getName());
    }
    if(dto.getCode() != null){
      entity.setCode(dto.getCode());
    }
    if(dto.getDescription() != null){
      entity.setDescription(dto.getDescription());
    }
    if(dto.getMaxCapacity() != null){
      entity.setMaxCapacity(dto.getMaxCapacity());
    }

  }

  public DepartmentResponseDto toResponseDto(DepartmentEntity entity){
    
    DepartmentResponseDto response = new DepartmentResponseDto();
    response.setId(entity.getId());
    response.setName(entity.getName());
    response.setCode(entity.getCode());
    response.setDescription(entity.getDescription());
    response.setMaxCapacity(entity.getMaxCapacity());
    return response;
  
  }

}
