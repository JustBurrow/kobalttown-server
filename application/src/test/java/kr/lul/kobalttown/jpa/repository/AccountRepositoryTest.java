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
public class AccountRepositoryTest {
  @Autowired
  private AccountRepository accountRepository;

  @Before
  public void setUp() throws Exception {
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
    String              email    = EmailUtils.random();
    final AccountEntity expected = new AccountEntity(email);

    // When
    final AccountEntity actual = this.accountRepository.save(expected);

    // Then
    assertThat(actual)
        .isNotNull()
        .extracting(Account::getEmail);
  }
}
