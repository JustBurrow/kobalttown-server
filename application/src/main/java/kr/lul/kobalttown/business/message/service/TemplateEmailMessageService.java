package kr.lul.kobalttown.business.message.service;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import kr.lul.kobalttown.business.message.exception.MessageException;
import kr.lul.kobalttown.business.message.service.params.EmailAddress;
import kr.lul.kobalttown.business.message.service.params.TemplateMessageParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

import static it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail.builder;
import static java.util.Arrays.asList;

/**
 * @author justburrow
 * @since 2017. 8. 31.
 */
@Service class TemplateEmailMessageService implements MessageService {
  private static final Logger log = LoggerFactory.getLogger(MessageService.class);

  @Autowired
  private EmailService emailService;

  @Override
  public void send(TemplateMessageParams message) throws MessageException {
    if (log.isTraceEnabled()) {
      log.trace(String.format("args : message=%s", message));
    }

    EmailAddress from = (EmailAddress) message.from();
    EmailAddress to   = (EmailAddress) message.to();
    Email        email;
    try {
      email = builder()
          .from(new InternetAddress(from.email(), from.name()))
          .to(asList(new InternetAddress(to.email(), to.name())))
          .subject(message.title())
          .body("")
          .encoding("UTF-8")
          .build();
    } catch (UnsupportedEncodingException e) {
      throw new MessageException("fail to build email.", e);
    }

    try {
      MimeMessage mimeMsg = this.emailService.send(email, message.template(), message.model());
      if (log.isTraceEnabled()) {
        log.trace(String.format("mimeMsg=%s", mimeMsg));
      }
    } catch (CannotSendEmailException e) {
      throw new MessageException("fail to send email.", e);
    }
  }
}
