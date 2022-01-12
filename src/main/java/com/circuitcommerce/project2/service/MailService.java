package com.circuitcommerce.project2.service;

import com.circuitcommerce.project2.model.NotificationEmail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
  private final JavaMailSender mailSender;
  private final MailContentBuilder mailContentBuilder;

  @Async
  void sendMail(NotificationEmail notificationEmail) {
    MimeMessagePreparator messagePreparator =
      mimeMessage -> {
        MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage);
        msgHelper.setFrom("donotreply@circuitcommerce.com");
        msgHelper.setTo(notificationEmail.getRecipient());
        msgHelper.setSubject(notificationEmail.getSubject());
        msgHelper.setText(
            mailContentBuilder.build(notificationEmail.getBody()));
      };
    try {
      mailSender.send(messagePreparator);
      log.info("Activation email sent!");
    } catch (MailException e) {
      throw new MailSendException(
          "Failed to send to " + notificationEmail.getRecipient());
    }
  }
}
