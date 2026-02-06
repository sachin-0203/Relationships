package com.phase3.Relationships.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {
  private LocalDateTime timeStamp;
  private int status;
  private String error;
  private String message;
  private String path;

}
