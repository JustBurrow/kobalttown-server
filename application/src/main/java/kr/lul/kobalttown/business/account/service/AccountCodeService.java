package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountCode;
import kr.lul.kobalttown.domain.account.AccountCodeReset;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Transactional
public interface AccountCodeService {
  /**
   * 계정 활성화 코드를 발급한다. 유효한 코드가 있을 경우, 삭제 후 재발급 한다.
   *
   * @param account
   * @return
   */
  AccountCode createAcitivateCode(Account account);

  /**
   * @param code
   * @return
   */
  AccountCode readActivateCode(String code);

  /**
   * 계정 재설정 코드를 발급한다. 기존 코드가 있을 경우, 삭제 후 재발급한다.
   *
   * @param account
   * @return
   */
  AccountCodeReset createReset(Account account);
}
