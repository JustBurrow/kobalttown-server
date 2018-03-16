package kr.lul.kobalttown.account.borderline;

import kr.lul.kobalttown.account.borderline.cmd.CreateAccountCmd;
import kr.lul.kobalttown.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.util.AssertionException;
import kr.lul.kobalttown.util.EmailUtils;
import kr.lul.kobalttown.util.TimeProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author justburrow
 * @since 2017. 8. 9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountBorderlinePackageTestConfiguration.class)
public class AccountBorderlineTest {
  @Autowired
  private AccountBorderline accountBorderline;

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private TimeProvider    timeProvider;

  private ZonedDateTime before;

  @Before
  public void setUp() throws Exception {
    assertThat(this.accountBorderline).isNotNull();
    assertThat(this.passwordEncoder).isNotNull();

    this.before = this.timeProvider.zonedDateTime();
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
    final AccountDto dto = this.accountBorderline.create(cmd).val();

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
