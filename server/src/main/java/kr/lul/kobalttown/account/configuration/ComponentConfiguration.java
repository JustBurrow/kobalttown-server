package kr.lul.kobalttown.account.configuration;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import kr.lul.kobalttown.util.SystemTimeProvider;
import kr.lul.kobalttown.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author justburrow
 * @since 2017. 8. 23.
 */
@Configuration
@EnableEmailTools
public class ComponentConfiguration {
  private static final Logger log = LoggerFactory.getLogger(ComponentConfiguration.class);

  @Bean
  public TimeProvider timeProvider() {
    return new SystemTimeProvider();
  }
}
