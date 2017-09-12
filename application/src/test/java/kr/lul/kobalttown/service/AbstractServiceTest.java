package kr.lul.kobalttown.service;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.service.account.AccountService;
import kr.lul.kobalttown.service.account.AccountServiceTestUtils;
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
  @Autowired
  protected AccountService  accountService;

  protected Instant before;

  protected void setUp() throws Exception {
    assertThat(this.timeProvider).isNotNull();
    assertThat(this.passwordEncoder).isNotNull();
    assertThat(this.accountService).isNotNull();

    this.before = this.timeProvider.now();
  }

  protected Account randomAccount() {
    return AccountServiceTestUtils.random(this.passwordEncoder, this.accountService);
  }
}
