package com.github.hemoptysisheart.spring.example.thymeleafform.service.cmd;

import java.util.StringJoiner;

public class SomeProcessCmd {
  private int number;
  private String text;
  private String email;
  private String password;
  private boolean error;
  private String field;

  public SomeProcessCmd() {
  }

  public SomeProcessCmd(int number, String text, String email, String password, boolean error, String field) {
    this.number = number;
    this.text = text;
    this.email = email;
    this.password = password;
    this.error = error;
    this.field = field;
  }

  public int getNumber() {
    return this.number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isError() {
    return this.error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public String getField() {
    return this.field;
  }

  public void setField(String field) {
    this.field = field;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SomeProcessCmd.class.getSimpleName() + "[", "]")
        .add("number=" + this.number)
        .add("text='" + this.text + "'")
        .add("email='" + this.email + "'")
        .add("password=[ PROTECTED ]")
        .add("error=" + this.error)
        .add("field='" + this.field + "'")
        .toString();
  }
}