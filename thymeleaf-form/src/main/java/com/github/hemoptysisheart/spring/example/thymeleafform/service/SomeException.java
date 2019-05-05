package com.github.hemoptysisheart.spring.example.thymeleafform.service;

public class SomeException extends Exception {
  private String field;

  public SomeException(String field) {
    this.field = field;
  }

  public SomeException(String message, String field) {
    super(message);
    this.field = field;
  }

  public SomeException(String message, Throwable cause, String field) {
    super(message, cause);
    this.field = field;
  }

  public SomeException(Throwable cause, String field) {
    super(cause);
    this.field = field;
  }

  public String getField() {
    return this.field;
  }
}