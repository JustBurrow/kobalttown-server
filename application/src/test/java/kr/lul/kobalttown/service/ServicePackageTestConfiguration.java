package kr.lul.kobalttown.service;

import kr.lul.kobalttown.dao.DaoPackageAnchor;
import kr.lul.kobalttown.jpa.JpaPackageAnchor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author justburrow
 * @since 2017. 8. 9.
 */
@SpringBootApplication(scanBasePackageClasses = {
    JpaPackageAnchor.class, DaoPackageAnchor.class,
    ServicePackageAnchor.class
})
public class ServicePackageTestConfiguration {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
