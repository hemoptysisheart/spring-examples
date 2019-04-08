package com.github.hemoptysisheart.spring.example.thymeleaf.web.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

@Controller
class IndexControllerImpl implements IndexController {
  private static final Logger log = getLogger(IndexControllerImpl.class);

  @Override
  public String index(@RequestParam(name = "color", defaultValue = "white") final String color, final Model model) {
    if (log.isTraceEnabled()) {
      log.trace("args : color={}, model={}", color, model);
    }

    model.addAttribute("colors", List.of("blue", "dark", "red", "white"));
    model.addAttribute("random", new Random());

    String template = format("layouts/%s", color);

    if (log.isTraceEnabled()) {
      log.trace("return : {}", template);
    }
    return template;
  }
}