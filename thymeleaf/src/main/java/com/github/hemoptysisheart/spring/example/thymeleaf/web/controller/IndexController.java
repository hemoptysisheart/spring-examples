package com.github.hemoptysisheart.spring.example.thymeleaf.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
public interface IndexController {
  @GetMapping("/")
  String index(@RequestParam(name = "color", defaultValue = "white") String color, Model model);
}