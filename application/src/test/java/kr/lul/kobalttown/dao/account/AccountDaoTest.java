package kr.lul.kobalttown.dao.account;

import kr.lul.kobalttown.dao.DaoPackageTestConfiguration;
import kr.lul.kobalttown.domain.Account;
import kr.lul.kobalttown.jpa.entity.AccountEntity;
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
@SpringBootTest(classes = DaoPackageTestConfiguration.class)
@Transactional
@Rollback
public class AccountDaoTest {
  @Autowired
  private AccountDao accountDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private Instant before;

  @Before
  public void setUp() throws Exception {
    assertThat(this.accountDao).isNotNull();
    assertThat(this.passwordEncoder).isNotNull();

    this.before = Instant.now();
  }

  @Test
  public void testInsertWithNull() throws Exception {
    assertThatThrownBy(() -> this.accountDao.insert(null))
        .isNotNull();
  }

  @Test
  public void testInsert() throws Exception {
    // Given
    final String  email    = EmailUtils.random();
    final String  password = this.passwordEncoder.encode(random(R.in(2, 20)));
    final Account expected = new AccountEntity(email, password);

    // When
    final Account actual = this.accountDao.insert(expected);

    // Then
    assertThat(actual.getId()).isGreaterThan(0L);
    assertThat(actual)
        .extracting(Account::getEmail, Account::getPassword)
        .containsExactly(email, password);
    assertThat(actual.getCreate())
        .isAfterOrEqualTo(this.before)
        .isEqualTo(actual.getUpdate());
  }
}
