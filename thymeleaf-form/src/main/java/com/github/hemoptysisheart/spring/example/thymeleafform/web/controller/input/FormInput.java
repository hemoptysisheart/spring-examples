package com.github.hemoptysisheart.spring.example.thymeleafform.web.controller.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class FormInput {
  @Min(0L)
  private int number;
  @NotNull
  private String text;
  @NotEmpty
  @Email
  private String email;
  @NotEmpty
  private String password;
  @NotEmpty
  private String passwordConfirm;
  /**
   * throws exception in service layer(package).
   */
  private boolean error = false;
  private String errSource;

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

  public String getPasswordConfirm() {
    return this.passwordConfirm;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
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

  public String getErrSource() {
    return this.errSource;
  }

  public void setErrSource(String errSource) {
    this.errSource = errSource;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", FormInput.class.getSimpleName() + "[", "]")
        .add("number=" + this.number)
        .add("text='" + this.text + "'")
        .add("email='" + this.email + "'")
        .add("password=[ PROTECTED ]")
        .add("passwordConfirm=[ PROTECTED ]")
        .add("error=" + this.error)
        .add("errSource='" + this.errSource + "'")
        .toString();
  }
}