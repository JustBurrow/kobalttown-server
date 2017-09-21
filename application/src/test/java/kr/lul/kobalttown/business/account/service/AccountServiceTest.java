package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.service.params.CreateAccountParams;
import kr.lul.kobalttown.business.account.service.params.UpdatePrincipalParams;
import kr.lul.kobalttown.domain.account.*;
import kr.lul.kobalttown.jpa.account.entity.AccountActivateCodeEntity;
import kr.lul.kobalttown.jpa.account.entity.AccountPrincipalEntity;
import kr.lul.kobalttown.jpa.account.repository.AccountActivateCodeRepository;
import kr.lul.kobalttown.jpa.account.repository.AccountPrincipalEmailRepository;
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

import java.time.Instant;

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
@SpringBootTest(classes = AccountServicePackageTestConfiguration.class)
@Transactional
@Rollback
public class AccountServiceTest extends AbstractAccountServiceTest {
  @Autowired
  private AccountActivateCodeRepository   accountActivateCodeRepository;
  @Autowired
  private AccountPrincipalEmailRepository accountPrincipalEmailRepository;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();

    assertThat(this.accountActivateCodeRepository).isNotNull();
    assertThat(this.accountPrincipalEmailRepository).isNotNull();
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
        new CreateAccountParams(null, randomAlphanumeric(R.in(1, 10)),
                                this.passwordEncoder.encode(random(R.in(1, 10))))))
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

    AccountActivateCodeEntity aac = this.accountActivateCodeRepository.findOneByAccount(account);
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

  @Test
  public void testUpdateEmailPassword() throws Exception {
    // Given
    final String                oldPassword = randomAlphanumeric(10);
    final Account               account     = randomAccount(oldPassword);
    final long                  accountId   = account.getId();
    final AccountPrincipalEmail old         = this.accountPrincipalEmailRepository.findOneByEmail(account.getEmail());
    final String                newPassword = this.passwordEncoder.encode(randomAlphanumeric(4, 20));
    final UpdatePrincipalParams params      = new UpdatePrincipalParams(account);
    params.setType(AccountPrincipalType.EMAIL_PASSWORD);
    params.setPublicKey(account.getEmail());
    params.setOldPrivateKey(oldPassword);
    params.setNewPrivateKey(newPassword);

    Thread.sleep(R.in(100L, 2000L));
    final Instant timestamp = this.timeProvider.now();

    // When
    final Account actual = this.accountService.update(params);

    // Then
    final AccountPrincipalEntity principal = this.accountPrincipalEmailRepository.findOneByEmail(account.getEmail());

    assertThat(actual)
        .isNotNull()
        .extracting(Account::getId, Account::getCreate)
        .containsExactly(accountId, account.getCreate());

    assertThat(principal)
        .isNotNull()
        .extracting(AccountPrincipal::getAccount, AccountPrincipal::getPublicKey, AccountPrincipal::getPrivateKey)
        .containsExactly(account, account.getEmail(), newPassword);
    assertThat(principal.getId())
        .isGreaterThan(old.getId());
    assertThat(principal.getCreate())
        .isAfterOrEqualTo(timestamp);
  }
}
