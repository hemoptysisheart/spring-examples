package com.github.hemoptysisheart.spring.example.thymeleaf.web.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
class IndexControllerImpl implements IndexController {
  private static final Logger log = getLogger(IndexControllerImpl.class);

  @Override
  public String index() {
    if (log.isTraceEnabled()) {
      log.trace("args : N/A");
    }

    return "layout/index";
  }
}