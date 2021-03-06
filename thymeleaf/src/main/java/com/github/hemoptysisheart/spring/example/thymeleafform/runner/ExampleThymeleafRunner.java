package com.github.hemoptysisheart.spring.example.thymeleafform.runner;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication(scanBasePackages = "com.github.hemoptysisheart.spring.example.thymeleaf")
@EnableWebMvc
public class ExampleThymeleafRunner {
  private static final Logger log = getLogger(ExampleThymeleafRunner.class);

  public static void main(String... args) {
    SpringApplication.run(ExampleThymeleafRunner.class, args);
    log.info("runner");
  }
}