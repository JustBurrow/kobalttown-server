package kr.lul.kobalttown.ms.account.borderline;

import kr.lul.kobalttown.business.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.ms.account.borderline.cmd.*;
import kr.lul.kobalttown.ms.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.util.Lazy;
import kr.lul.kobalttown.util.PropertyException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Transactional
public interface AccountBorderline {
  Lazy<AccountDto> create(CreateAccountCmd cmd);

  Lazy<AccountDto> read(long id);

  Lazy<AccountDto> update(UpdateAccountBasicCmd cmd) throws PropertyException;

  Lazy<AccountDto> update(UpdatePasswordCmd cmd);

  Lazy<AccountDto> activate(String code) throws IllegalAccountActivateCodeException;

  /**
   * @param cmd
   * @return
   * @@since 2017. 10. 8.
   */
  Lazy<AccountDto> issue(IssueAccountActivateCode cmd);

  /**
   * @param cmd
   * @return
   * @@since 2017. 9. 28.
   */
  Lazy<AccountDto> issue(IssueAccountResetCodeCmd cmd);
}
