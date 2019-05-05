package com.github.hemoptysisheart.spring.example.thymeleafform.service;

import com.github.hemoptysisheart.spring.example.thymeleafform.service.cmd.SomeProcessCmd;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;
import static org.slf4j.LoggerFactory.getLogger;

@Service
class SomeServiceImpl implements SomeService {
  private static final Logger log = getLogger(SomeServiceImpl.class);
  private static final Set<String> FIELD_NAMES = unmodifiableSet(
      new HashSet<>(asList("number", "text", "email", "password")));

  @Override
  public void process(SomeProcessCmd cmd) throws SomeException {
    if (log.isTraceEnabled()) {
      log.trace("args : cmd={}", cmd);
    }

    SomeException exception;
    if (!cmd.isError()) {
      exception = null;
    } else if (FIELD_NAMES.contains(cmd.getField())) {
      exception = new SomeException(cmd.getField() + " has some error.", cmd.getField());
    } else {
      exception = new SomeException("There is unknown service logic error : '" + cmd.getField() + "'", null);
    }

    if (null != exception) {
      throw exception;
    }
  }
}