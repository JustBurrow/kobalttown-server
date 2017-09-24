package kr.lul.kobalttown.ms.account.borderline;

import kr.lul.kobalttown.business.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.ms.account.borderline.cmd.CreateAccountCmd;
import kr.lul.kobalttown.ms.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.util.Lazy;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Transactional
public interface AccountBorderline {
  Lazy<AccountDto> create(CreateAccountCmd cmd);

  Lazy<AccountDto> read(long id);

  Lazy<AccountDto> activate(String code) throws IllegalAccountActivateCodeException;
}
