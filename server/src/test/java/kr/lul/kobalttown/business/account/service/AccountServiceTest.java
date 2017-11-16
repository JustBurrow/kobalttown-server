package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.service.params.CreateAccountParams;
import kr.lul.kobalttown.business.account.service.params.UpdatePrincipalParams;
import kr.lul.kobalttown.domain.account.*;
import kr.lul.kobalttown.jpa.account.entity.AbstractAccountCode;
import kr.lul.kobalttown.jpa.account.entity.AccountPrincipalEntity;
import kr.lul.kobalttown.jpa.account.repository.AccountCodeRepository;
import kr.lul.kobalttown.jpa.account.repository.AccountPrincipalRepository;
import kr.lul.kobalttown.util.AssertionException;
import kr.lul.kobalttown.util.EmailUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
  @Value("${kobalttown.lul.kr.test.email.target-domain}")
  private String domain;

  @Autowired
  private AccountCodeRepository      accountCodeRepository;
  @Autowired
  private AccountPrincipalRepository accountPrincipalRepository;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();

    assertThat(this.accountCodeRepository).isNotNull();
    assertThat(this.accountPrincipalRepository).isNotNull();
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
        .hasCauseInstanceOf(NullPointerException.class)
        .hasMessage("email");
  }

  @Test
  public void testCreateWithNullPassword() throws Exception {
    // Given
    final String              email  = EmailUtils.random(this.domain);
    final CreateAccountParams params = new CreateAccountParams(email, randomAlphanumeric(R.in(1, 10)), null);

    // When & Then
    assertThatThrownBy(() -> this.accountService.create(params))
        .isInstanceOf(AssertionException.class)
        .hasCauseInstanceOf(NullPointerException.class)
        .hasMessage("privateKey");
  }

  @Test
  public void testCreateWithIllegalPassword() throws Exception {
    // Given
    final String password = this.passwordEncoder.encode(random(R.in(1, 20))).substring(1) + "*";
    final CreateAccountParams params = new CreateAccountParams(
        EmailUtils.random(this.domain), randomAlphanumeric(R.in(1, 10)), password);

    // When & Then
    assertThatThrownBy(() -> this.accountService.create(params))
        .isInstanceOf(AssertionException.class)
        .hasMessage("privateKey");
  }

  @Test
  public void testCreateWithRandom() throws Exception {
    // Given
    final String email    = EmailUtils.random(this.domain);
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

    AbstractAccountCode aac = this.accountCodeRepository.findOneByAccount(account);
    assertThat(aac)
        .isNotNull()
        .extracting(AccountCode::getAccount, AccountCode::getUsed)
        .containsExactly(account, null);
  }

  @Test
  public void testCreateWithDuplicatedEmail() throws Exception {
    // Given
    final String email = EmailUtils.random(this.domain);
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
    final String  oldPassword = randomAlphanumeric(10);
    final Account account     = randomAccount(oldPassword);
    final long    accountId   = account.getId();
    final AccountPrincipalEmail old = (AccountPrincipalEmail) this.accountPrincipalRepository.findOneByPublicKey(
        account.getEmail());
    final String                newPassword = randomAlphanumeric(4, 20);
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
    final AccountPrincipalEntity principal = this.accountPrincipalRepository.findOneByPublicKey(account.getEmail());

    assertThat(actual)
        .isNotNull()
        .extracting(Account::getId, Account::getCreate)
        .containsExactly(accountId, account.getCreate());

    assertThat(principal)
        .isNotNull()
        .extracting(AccountPrincipal::getAccount, AccountPrincipal::getPublicKey)
        .containsExactly(account, account.getEmail());
    assertThat(this.passwordEncoder.matches(newPassword, principal.getPrivateKey()))
        .isTrue();
    assertThat(principal.getId())
        .isGreaterThan(old.getId());
    assertThat(principal.getCreate())
        .isAfterOrEqualTo(timestamp);
  }
}
