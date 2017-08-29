package kr.lul.kobalttown.borderline.account;

import kr.lul.kobalttown.borderline.BorderlinePackageTestConfiguration;
import kr.lul.kobalttown.borderline.account.cmd.CreateAccountCmd;
import kr.lul.kobalttown.borderline.account.dto.AccountDto;
import kr.lul.kobalttown.util.AssertionException;
import kr.lul.kobalttown.util.EmailUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author justburrow
 * @since 2017. 8. 9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BorderlinePackageTestConfiguration.class)
public class AccountBorderlineTest {
  @Autowired
  private AccountBorderline accountBorderline;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private Instant before;

  @Before
  public void setUp() throws Exception {
    assertThat(this.accountBorderline).isNotNull();
    assertThat(this.passwordEncoder).isNotNull();

    this.before = Instant.now();
  }

  @Test
  public void testCreateWithNull() throws Exception {
    assertThatThrownBy(() -> this.accountBorderline.create(null))
        .isInstanceOf(AssertionException.class)
        .hasMessage("cmd");
  }

  @Test
  public void testCreate() throws Exception {
    // Given
    final String email = EmailUtils.random();
    final String name  = randomAlphanumeric(1, 20);
    final CreateAccountCmd cmd = new CreateAccountCmd(
        email, name, this.passwordEncoder.encode(randomAlphanumeric(4, 20)));

    // When
    final AccountDto dto = this.accountBorderline.create(cmd);

    // Then
    assertThat(dto.getId())
        .isGreaterThan(0L);
    assertThat(dto)
        .extracting(AccountDto::getEmail, AccountDto::getName, AccountDto::isEnabled)
        .containsExactly(email, name, false);
    assertThat(dto.getCreate())
        .isNotNull()
        .isAfterOrEqualTo(this.before)
        .isEqualTo(dto.getUpdate());
  }
}
