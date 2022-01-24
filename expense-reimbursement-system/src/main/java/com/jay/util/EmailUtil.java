package com.jay.util;

import com.jay.model.User;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EmailUtil {
  public static Mailer mailBuilder = MailerBuilder
      .withSMTPServer("smtp.mailtrap.io", 2525, "c813367cbd3783", "6c8767a7847074")
      .buildMailer();

  private static void sendEmail(Email email) {
    mailBuilder.sendMail(email);
  }

  public static void buildEmailFor(User user) {
    Email email = EmailBuilder.startingBlank()
        .from("System Administrator", "SysAdmin@ers.net")
        .to(user.getFirstName() + user.getLastName(), user.getEmail())
        .withSubject("You have been registered for the Expense Reimbursement System")
        .withPlainText("Your manager has signed you up to start reimbursing, here is your temp password: " + "Pass1234")
        .buildEmail();

    sendEmail(email);
  }
}
