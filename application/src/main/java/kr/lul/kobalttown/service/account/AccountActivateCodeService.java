package kr.lul.kobalttown.service.account;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountActivateCode;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Transactional
public interface AccountActivateCodeService {
  /**
   * 계정 활성화 코드를 발급한다. 유효한 코드가 있을 경우, 삭제 후 재발급 한다.
   *
   * @param account
   * @return
   */
  AccountActivateCode create(Account account);

  /**
   * @param code
   * @return
   */
  AccountActivateCode read(String code);
}
