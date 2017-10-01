package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountCode;
import kr.lul.kobalttown.util.AssertionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static kr.lul.kobalttown.domain.account.AccountCode.TTL;
import static kr.lul.kobalttown.util.RandomUtil.R;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountServicePackageTestConfiguration.class)
@Transactional
public class AccountCodeServiceTest extends AbstractAccountServiceTest {
  @Autowired
  private AccountCodeService accountCodeService;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();

    assertThat(this.accountCodeService).isNotNull();
  }

  @Test
  public void testCreateWithNull() throws Exception {
    assertThatThrownBy(() -> this.accountCodeService.createAcitivateCode(null))
        .isNotNull()
        .isInstanceOf(AssertionException.class)
        .hasMessageContaining("account");
  }

  /**
   * 계정 생성시 발급된 활성화 코드를 폐기하고 새로 발급하는 테스트.
   *
   * @throws Exception
   */
  @Test
  public void testCreate() throws Exception {
    // Given
    final Account account = randomAccount();

    // When
    Thread.sleep(R.positive(2000L));
    final Instant     timestamp = this.timeProvider.now();
    final AccountCode code      = this.accountCodeService.createAcitivateCode(account);

    // Then
    assertThat(code)
        .isNotNull()
        .extracting(AccountCode::getAccount, AccountCode::getUsed, AccountCode::isUsed)
        .containsExactly(account, null, false);
    assertThat(code.getId())
        .isGreaterThan(0L);
    assertThat(code.getExpire())
        .isNotNull()
        .isAfterOrEqualTo(timestamp.plusMillis(TTL));
    assertThat(code.getCreate())
        .isNotNull()
        .isAfterOrEqualTo(timestamp)
        .isEqualTo(code.getUpdate());
  }
}
