package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseForm extends DTO {

  private int code;
  private String message;
  private Object data;


}
