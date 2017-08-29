package kr.lul.kobalttown.service;

import kr.lul.kobalttown.util.TimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author justburrow
 * @since 2017. 8. 26.
 */
public class AbstractServiceTest {
  @Autowired
  protected TimeProvider    timeProvider;
  @Autowired
  protected PasswordEncoder passwordEncoder;

  protected Instant before;

  protected void abstractSetUp() throws Exception {
    assertThat(timeProvider).isNotNull();
    assertThat(passwordEncoder).isNotNull();

    before = timeProvider.now();
  }
}
