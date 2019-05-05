package com.github.hemoptysisheart.spring.example.thymeleafform.service;

import com.github.hemoptysisheart.spring.example.thymeleafform.service.cmd.SomeProcessCmd;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

@Service
class SomeServiceImpl implements SomeService {
  private static final Set<String> FIELD_NAMES = unmodifiableSet(
      new HashSet<>(asList("number", "text", "email", "password")));

  @Override
  public void process(SomeProcessCmd cmd) throws SomeException {
    if (!cmd.isError()) {
      return;
    }

    if (FIELD_NAMES.contains(cmd.getField())) {
      throw new SomeException(cmd.getField() + " has some error.", cmd.getField());
    } else {
      throw new SomeException("there is unknown error : '" + cmd.getField() + "'", null);
    }
  }
}