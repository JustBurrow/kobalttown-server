package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.business.account.service.params.*;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.util.PropertyException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Transactional
public interface AccountService {
  Account create(CreateAccountParams params);

  Account read(long id);

  Account activate(String code) throws IllegalAccountActivateCodeException;

  Account update(UpdateAccountParams params) throws PropertyException;

  Account update(UpdatePrincipalParams params);

  /**
   * @param params
   * @return
   * @@since 2017. 10. 8.
   */
  Account issue(IssueAccountActivateCodeParams params);

  /**
   * 연락용 E-Mail를 기준으로 계정을 재설정할 수 있는 코드를 만들고, 연락용 메일로 코드를 전송한다.
   *
   * @param params
   * @return
   * @@since 2017. 9. 28.
   */
  Account issue(IssueAccountResetCodeParams params);
}
