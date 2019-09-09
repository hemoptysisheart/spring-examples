package com.github.hemoptysisheart.spring.example.grpc.web.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author hemoptysisheart
 * @since 2019/09/01
 */
@Controller
@RequestMapping
public class RootController {
  private static final Logger log = getLogger(RootController.class);

  @GetMapping
  public String index(Model model) {
    if (log.isTraceEnabled())
      log.trace("args : model={}", model);

    return "layout/_/index";
  }
}
