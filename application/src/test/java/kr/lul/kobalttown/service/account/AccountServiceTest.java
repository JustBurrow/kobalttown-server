package kr.lul.kobalttown.service.account;

import kr.lul.kobalttown.service.ServicePackageTestConfiguration;
import kr.lul.kobalttown.service.account.params.CreateAccountParams;
import kr.lul.kobalttown.util.AssertionException;
import kr.lul.kobalttown.util.EmailUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static kr.lul.kobalttown.util.RandomUtil.R;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author justburrow
 * @since 2017. 8. 9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServicePackageTestConfiguration.class)
@Transactional
@Rollback
public class AccountServiceTest {
  @Autowired
  private AccountService accountService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private Instant before;

  @Before
  public void setUp() throws Exception {
    assertThat(this.accountService).isNotNull();
    assertThat(this.passwordEncoder).isNotNull();

    this.before = Instant.now();
  }

  @Test
  public void testCreateWithNull() throws Exception {
    assertThatThrownBy(() -> this.accountService.create(null))
        .isInstanceOf(AssertionException.class)
        .hasMessage("params");
  }

  @Test
  public void testCreateWithNullEmail() throws Exception {
    // Given
    final String              password = this.passwordEncoder.encode(random(R.in(1, 20)));
    final CreateAccountParams params   = new CreateAccountParams(null, password);

    // When & Then
    assertThatThrownBy(() -> this.accountService.create(params))
        .isInstanceOf(AssertionException.class)
        .hasMessage("params.email");
  }

  @Test
  public void testCreateWithNullPassword() throws Exception {
    // Given
    final String              email  = EmailUtils.random();
    final CreateAccountParams params = new CreateAccountParams(email, null);

    // When & Then
    assertThatThrownBy(() -> this.accountService.create(params))
        .isInstanceOf(AssertionException.class)
        .hasMessage("params.password");
  }

  @Test
  public void testCreateWithIllegalPassword() throws Exception {
    // Given
    final String              email    = EmailUtils.random();
    final String              password = this.passwordEncoder.encode(random(R.in(1, 20))).substring(1) + "*";
    final CreateAccountParams params   = new CreateAccountParams(email, password);

    // When & Then
    assertThatThrownBy(() -> this.accountService.create(params))
        .isInstanceOf(AssertionException.class)
        .hasMessage("params.password");
  }
}
