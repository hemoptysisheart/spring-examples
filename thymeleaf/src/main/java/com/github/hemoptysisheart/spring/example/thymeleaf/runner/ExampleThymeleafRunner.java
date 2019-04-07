package com.github.hemoptysisheart.spring.example.thymeleaf.runner;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication(scanBasePackages = "com.github.hemoptysisheart.spring.example.thymeleaf")
public class ExampleThymeleafRunner {
  private static final Logger log = getLogger(ExampleThymeleafRunner.class);

  public static void main(String... args) {
    SpringApplication.run(ExampleThymeleafRunner.class, args);
    log.info("runner");
  }
}