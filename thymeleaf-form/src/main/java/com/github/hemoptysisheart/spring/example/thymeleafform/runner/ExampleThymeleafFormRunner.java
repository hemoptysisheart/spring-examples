package com.github.hemoptysisheart.spring.example.thymeleafform.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.github.hemoptysisheart.spring.example.thymeleafform")
@EnableWebMvc
public class ExampleThymeleafFormRunner {
  public static void main(String[] args) {
    SpringApplication.run(ExampleThymeleafFormRunner.class, args);
  }
}