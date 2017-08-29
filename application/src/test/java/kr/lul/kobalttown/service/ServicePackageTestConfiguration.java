package kr.lul.kobalttown.service;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import kr.lul.kobalttown.dao.DaoPackageAnchor;
import kr.lul.kobalttown.jpa.JpaPackageAnchor;
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
    scanBasePackageClasses = {JpaPackageAnchor.class, DaoPackageAnchor.class, ServicePackageAnchor.class})
@EnableEmailTools
public class ServicePackageTestConfiguration {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public TimeProvider timeProvider() {
    return new SystemTimeProvider();
  }
}
