package kr.lul.kobalttown.service;

import kr.lul.kobalttown.dao.DaoPackageAnchor;
import kr.lul.kobalttown.jpa.JpaPackageAnchor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

/**
 * @author justburrow
 * @since 2017. 8. 9.
 */
@SpringBootApplication(scanBasePackageClasses = {
    JpaPackageAnchor.class, DaoPackageAnchor.class,
    ServicePackageAnchor.class
})
public class ServicePackageTestConfiguration {
  @Value("${kobalttown.lul.kr.mail.username}")
  private String mailUsername;
  @Value("${kobalttown.lul.kr.mail.password}")
  private String mailPassword;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setProtocol("smtp");
    sender.setHost("smtp.gmail.com");
    sender.setPort(587);
    sender.setUsername(mailUsername);
    sender.setPassword(mailPassword);

    Properties properties = sender.getJavaMailProperties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.starttls.enable", "true");
    properties.setProperty("mail.debug", "true");

    return sender;
  }
}
