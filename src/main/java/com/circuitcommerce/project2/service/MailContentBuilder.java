package com.circuitcommerce.project2.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {
  private final TemplateEngine templateEngine;

  // TODO figure out how to create a link
  String build(String message) {
    Context context = new Context();
    context.setVariable("message", message);
    return templateEngine.process("mailTemplate", context);
  }
}
