package kr.lul.kobalttown.borderline.account;

import kr.lul.kobalttown.borderline.account.cmd.CreateAccountCmd;
import kr.lul.kobalttown.borderline.account.dto.AccountDto;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.service.account.AccountService;
import kr.lul.kobalttown.service.account.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.service.account.params.CreateAccountParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * @author justburrow
 * @since 2017. 8. 8.
 */
@Service class AccountBorderlineImpl implements AccountBorderline {
  private static final Logger log = LoggerFactory.getLogger(AccountBorderline.class);

  @Autowired
  private AccountService accountService;

  @Override
  public AccountDto create(CreateAccountCmd cmd) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : cmd=%s", cmd));
    }
    notNull(cmd, "cmd");

    CreateAccountParams params  = new CreateAccountParams(cmd.getEmail(), cmd.getName(), cmd.getPassword());
    Account             account = this.accountService.create(params);

    AccountDto dto = new AccountDto(account);
    if (log.isTraceEnabled()) {
      log.trace(format("result : dto=%s", dto));
    }
    return dto;
  }

  @Override
  public AccountDto activate(String code) throws IllegalAccountActivateCodeException {
    if (log.isTraceEnabled()) {
      log.trace(format("activate args : code='%s'", code));
    }

    Account    account = this.accountService.activate(code);
    AccountDto dto     = new AccountDto(account);

    if (log.isTraceEnabled()) {
      log.trace(String.format("activate return : %s", dto));
    }
    return dto;
  }
}
