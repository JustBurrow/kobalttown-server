package kr.lul.kobalttown.service.account;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountActivateCode;
import kr.lul.kobalttown.service.AbstractServiceTest;
import kr.lul.kobalttown.service.ServicePackageTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static kr.lul.kobalttown.util.RandomUtil.R;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServicePackageTestConfiguration.class)
@Transactional
public class AccountActivateCodeServiceTest extends AbstractServiceTest {
  @Autowired
  private AccountActivateCodeService accountActivateCodeService;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();

    assertThat(this.accountActivateCodeService).isNotNull();
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
    final Instant             timestamp = this.timeProvider.now();
    final AccountActivateCode code      = this.accountActivateCodeService.create(account);

    // Then
    assertThat(code)
        .isNotNull()
        .extracting(AccountActivateCode::getAccount, AccountActivateCode::getUsed, AccountActivateCode::isUsed)
        .containsExactly(account, null, false);
    assertThat(code.getId())
        .isGreaterThan(0L);
    assertThat(code.getExpire())
        .isNotNull()
        .isAfterOrEqualTo(this.before.plusMillis(AccountActivateCode.TTL));
    assertThat(code.getCreate())
        .isNotNull()
        .isAfterOrEqualTo(timestamp)
        .isEqualTo(code.getUpdate());
  }
}
