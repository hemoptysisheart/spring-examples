package com.github.hemoptysisheart.spring.example.thymeleafform.service;

import com.github.hemoptysisheart.spring.example.thymeleafform.service.cmd.SomeProcessCmd;

public interface SomeService {
  void process(SomeProcessCmd cmd) throws SomeException;
}