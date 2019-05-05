package com.github.hemoptysisheart.spring.example.thymeleafform.web.controller;

import com.github.hemoptysisheart.spring.example.thymeleafform.web.controller.input.FormInput;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/")
public interface IndexController {
  @GetMapping
  String form(final Model model);

  @PostMapping
  String submit(@ModelAttribute @Valid FormInput input, BindingResult result, Model model);
}