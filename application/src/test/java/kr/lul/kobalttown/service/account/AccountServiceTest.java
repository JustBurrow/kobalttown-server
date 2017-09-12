package kr.lul.kobalttown.service.account;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountActivateCode;
import kr.lul.kobalttown.jpa.entity.AccountActivateCodeEntity;
import kr.lul.kobalttown.jpa.repository.AccountActivateCodeRepository;
import kr.lul.kobalttown.service.AbstractServiceTest;
import kr.lul.kobalttown.service.ServicePackageTestConfiguration;
import kr.lul.kobalttown.service.account.params.CreateAccountParams;
import kr.lul.kobalttown.util.AssertionException;
import kr.lul.kobalttown.util.EmailUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static kr.lul.kobalttown.util.RandomUtil.R;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
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
public class AccountServiceTest extends AbstractServiceTest {
  @Autowired
  private AccountActivateCodeRepository accountActivateCodeRepository;

  @Before
  public void setUp() throws Exception {
    super.setUp();
  }

  @Test
  public void testCreateWithNull() throws Exception {
    assertThatThrownBy(() -> this.accountService.create(null))
        .isInstanceOf(AssertionException.class)
        .hasMessage("params");
  }

  @Test
  public void testCreateWithNullEmail() throws Exception {
    // When & Then
    assertThatThrownBy(() -> this.accountService.create(
        new CreateAccountParams(null, randomAlphanumeric(R.in(1, 10)), passwordEncoder.encode(random(R.in(1, 10))))))
        .isInstanceOf(AssertionException.class)
        .hasMessage("params.email");
  }

  @Test
  public void testCreateWithNullPassword() throws Exception {
    // Given
    final String              email  = EmailUtils.random();
    final CreateAccountParams params = new CreateAccountParams(email, randomAlphanumeric(R.in(1, 10)), null);

    // When & Then
    assertThatThrownBy(() -> this.accountService.create(params))
        .isInstanceOf(AssertionException.class)
        .hasMessage("params.password");
  }

  @Test
  public void testCreateWithIllegalPassword() throws Exception {
    // Given
    final String password = this.passwordEncoder.encode(random(R.in(1, 20))).substring(1) + "*";
    final CreateAccountParams params = new CreateAccountParams(
        EmailUtils.random(), randomAlphanumeric(R.in(1, 10)), password);

    // When & Then
    assertThatThrownBy(() -> this.accountService.create(params))
        .isInstanceOf(AssertionException.class)
        .hasMessage("params.password");
  }

  @Test
  public void testCreateWithRandom() throws Exception {
    // Given
    final String email    = EmailUtils.random();
    final String name     = randomAlphanumeric(R.in(1, 20));
    final String password = this.passwordEncoder.encode(random(R.in(1, 20)));

    // When
    final Account account = this.accountService.create(new CreateAccountParams(email, name, password));

    // Then
    assertThat(account)
        .isNotNull()
        .extracting(Account::getEmail, Account::getName, Account::isEnable)
        .containsExactly(email, name, false);
    assertThat(account.getId())
        .isGreaterThan(0L);
    assertThat(account.getCreate())
        .isAfterOrEqualTo(this.before)
        .isEqualTo(account.getUpdate());

    AccountActivateCodeEntity aac = accountActivateCodeRepository.findOneByAccount(account);
    assertThat(aac)
        .isNotNull()
        .extracting(AccountActivateCode::getAccount, AccountActivateCode::getUsed)
        .containsExactly(account, null);
  }

  @Test
  public void testCreateWithDuplicatedEmail() throws Exception {
    // Given
    final String email = EmailUtils.random();
    this.accountService.create(
        new CreateAccountParams(email, randomAlphanumeric(1, 20), this.passwordEncoder.encode(random(R.in(1, 20)))));

    // When & Then
    assertThatThrownBy(
        () -> this.accountService.create(
            new CreateAccountParams(email, randomAlphanumeric(2, 20),
                                    this.passwordEncoder.encode(random(R.in(1, 20))))
        )).isNotNull();
  }
}
