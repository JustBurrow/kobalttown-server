package kr.lul.kobalttown.borderline.account;

import kr.lul.kobalttown.borderline.account.cmd.CreateAccountCmd;
import kr.lul.kobalttown.borderline.account.dto.AccountDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Transactional
public interface AccountBorderline {
  AccountDto create(CreateAccountCmd cmd);
}
