package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataException extends RuntimeException {

  protected String message;
  protected int code;

  public static class ResourceNotFoundException extends DataException {

    public ResourceNotFoundException() {
      super("Not found", 1);
    }
  }

  public static class NotExistDataException extends DataException {

    public NotExistDataException() {
      super("data is empty", 2);
    }
  }

}
