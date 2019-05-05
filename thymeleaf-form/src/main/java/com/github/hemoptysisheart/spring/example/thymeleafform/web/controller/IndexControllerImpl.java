package com.github.hemoptysisheart.spring.example.thymeleafform.web.controller;

import com.github.hemoptysisheart.spring.example.thymeleafform.service.SomeException;
import com.github.hemoptysisheart.spring.example.thymeleafform.service.SomeService;
import com.github.hemoptysisheart.spring.example.thymeleafform.service.cmd.SomeProcessCmd;
import com.github.hemoptysisheart.spring.example.thymeleafform.web.configuration.ModelAttributes;
import com.github.hemoptysisheart.spring.example.thymeleafform.web.configuration.ViewNames;
import com.github.hemoptysisheart.spring.example.thymeleafform.web.controller.input.FormInput;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
class IndexControllerImpl implements IndexController {
  private static final Logger log = getLogger(IndexControllerImpl.class);

  @Autowired
  private SomeService someService;

  private String doForm(Model model) {
    if (model.containsAttribute(ModelAttributes.INPUT_NAME)) {
      ((FormInput) model.asMap().get(ModelAttributes.INPUT_NAME)).clearError();
    } else {
      model.addAttribute(ModelAttributes.INPUT_NAME, new FormInput());
    }

    if (!model.containsAttribute(ModelAttributes.ERROR_SOURCES)) {
      model.addAttribute(ModelAttributes.ERROR_SOURCES,
          Arrays.asList("number", "text", "email", "password", "passwordConfirm"));
    }
    return ViewNames.FORM;
  }

  private String doSubmit(FormInput input, BindingResult result, Model model) {
    SomeProcessCmd cmd = new SomeProcessCmd(input.getNumber(), input.getText(), input.getEmail(), input.getPassword(),
        input.isError(), input.getErrSource());
    String template;
    try {
      this.someService.process(cmd);
      template = "redirect:/";
    } catch (SomeException e) {
      if (null == e.getField()) {
        result.addError(new ObjectError(ModelAttributes.INPUT_NAME, e.getMessage()));
      } else {
        result.addError(new FieldError(ModelAttributes.INPUT_NAME, e.getField(), e.getMessage()));
      }
      template = doForm(model);
    }
    return template;
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  public String form(Model model) {

    String template = doForm(model);

    return template;
  }

  @Override
  public String submit(
      @ModelAttribute(ModelAttributes.INPUT_NAME) @Valid final FormInput input, final BindingResult result,
      final Model model) {
    if (log.isTraceEnabled()) {
      log.trace("args : input={}, result={}, model={}", input, result, model);
    }

    String password = input.getPassword();
    if (null != password && !password.equals(input.getPasswordConfirm())) {
      result.addError(new FieldError(ModelAttributes.INPUT_NAME, "passwordConfirm",
          "Password confirm does not match."));
    }

    String template = result.hasErrors()
        ? doForm(model)
        : doSubmit(input, result, model);

    if (log.isTraceEnabled()) {
      log.trace("result : template={}", template);
      log.trace("result : result={}", result);
      log.trace("result : model={}", model);
    }
    return template;
  }
}