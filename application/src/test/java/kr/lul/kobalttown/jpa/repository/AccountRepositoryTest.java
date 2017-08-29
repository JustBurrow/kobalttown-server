package kr.lul.kobalttown.jpa.repository;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.jpa.JpaPackageTestConfiguration;
import kr.lul.kobalttown.jpa.entity.AccountEntity;
import kr.lul.kobalttown.util.EmailUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author justburrow
 * @since 2017. 8. 5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaPackageTestConfiguration.class)
@Transactional
@Rollback
public class AccountRepositoryTest extends AbstractRepositoryTest {
  @Autowired
  private AccountRepository accountRepository;

  @Before
  public void setUp() throws Exception {
    commonSetUp();

    assertThat(this.accountRepository).isNotNull();
  }

  @Test
  public void testSaveWithNull() throws Exception {
    assertThatThrownBy(() -> this.accountRepository.save((AccountEntity) null))
        .isNotNull()
        .hasCauseInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("must not be null");
  }

  @Test
  public void testSaveWithRandomAccount() throws Exception {
    // Given
    final String        email    = EmailUtils.random();
    final String        name     = randomAlphanumeric(1, 10);
    final AccountEntity expected = new AccountEntity(email, name);

    // When
    final AccountEntity actual = this.accountRepository.save(expected);

    // Then
    assertThat(actual)
        .isNotNull()
        .extracting(Account::getEmail, AccountEntity::getName)
        .containsExactly(email, name);
    assertThat(actual.getId())
        .isGreaterThan(0L);
    assertThat(actual.getCreate())
        .isNotNull()
        .isAfterOrEqualTo(before)
        .isEqualTo(actual.getUpdate());
  }
}
