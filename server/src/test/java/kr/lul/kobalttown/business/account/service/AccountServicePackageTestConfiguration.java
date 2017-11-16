package kr.lul.kobalttown.business.account.service;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import kr.lul.kobalttown.business.account.AccountBusinessAnchor;
import kr.lul.kobalttown.business.configuration.BusinessConfigurationPackageAnchor;
import kr.lul.kobalttown.business.message.MessageBusinessAnchor;
import kr.lul.kobalttown.jpa.account.AccountJpaAnchor;
import kr.lul.kobalttown.util.SystemTimeProvider;
import kr.lul.kobalttown.util.TimeProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author justburrow
 * @since 2017. 8. 9.
 */
@SpringBootApplication(
    scanBasePackageClasses = {
        BusinessConfigurationPackageAnchor.class,
        AccountJpaAnchor.class,
        MessageBusinessAnchor.class,
        AccountBusinessAnchor.class
    })
@EnableEmailTools
public class AccountServicePackageTestConfiguration {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public TimeProvider timeProvider() {
    return new SystemTimeProvider();
  }
}
