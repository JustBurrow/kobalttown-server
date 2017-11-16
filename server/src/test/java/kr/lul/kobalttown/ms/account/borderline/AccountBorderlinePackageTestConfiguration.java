package kr.lul.kobalttown.ms.account.borderline;

import kr.lul.kobalttown.business.account.AccountBusinessAnchor;
import kr.lul.kobalttown.business.configuration.BusinessConfigurationPackageAnchor;
import kr.lul.kobalttown.business.message.MessageBusinessAnchor;
import kr.lul.kobalttown.jpa.account.AccountJpaAnchor;
import kr.lul.kobalttown.ms.account.AccountMicroserviceAnchor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author justburrow
 * @since 2017. 8. 9.
 */
@SpringBootApplication(scanBasePackageClasses = {
    BusinessConfigurationPackageAnchor.class,
    AccountJpaAnchor.class,
    MessageBusinessAnchor.class,
    AccountBusinessAnchor.class,
    AccountMicroserviceAnchor.class
})
public class AccountBorderlinePackageTestConfiguration {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
