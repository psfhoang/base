package com.example.demo.dto;

import java.io.Serializable;

public abstract class DTO implements Serializable, Cloneable {

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}



