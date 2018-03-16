package kr.lul.kobalttown.account.dao;

import kr.lul.kobalttown.account.configuration.BusinessConfigurationPackageAnchor;
import kr.lul.kobalttown.business.account.AccountBusinessAnchor;
import kr.lul.kobalttown.message.MessageBusinessAnchor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author justburrow
 * @since 2017. 8. 9.
 */
@SpringBootApplication(scanBasePackageClasses = {
    BusinessConfigurationPackageAnchor.class, MessageBusinessAnchor.class, AccountBusinessAnchor.class
})
public class DaoPackageTestConfiguration {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
