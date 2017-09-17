package kr.lul.kobalttown.ms.account.borderline;

import kr.lul.kobalttown.business.account.service.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.ms.account.borderline.cmd.CreateAccountCmd;
import kr.lul.kobalttown.ms.account.borderline.dto.AccountDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Transactional
public interface AccountBorderline {
  AccountDto create(CreateAccountCmd cmd);

  AccountDto activate(String code) throws IllegalAccountActivateCodeException;
}
