package kr.lul.kobalttown.ms.account.borderline;

import kr.lul.kobalttown.business.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.business.account.service.AccountService;
import kr.lul.kobalttown.business.account.service.params.CreateAccountParams;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.ms.account.borderline.cmd.CreateAccountCmd;
import kr.lul.kobalttown.ms.account.borderline.converter.AccountConverter;
import kr.lul.kobalttown.ms.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.util.Lazy;
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
  private AccountService   accountService;
  @Autowired
  private AccountConverter accountConverter;

  @Override
  public Lazy<AccountDto> create(CreateAccountCmd cmd) {
    if (log.isTraceEnabled()) {
      log.trace(format("create args : cmd=%s", cmd));
    }
    notNull(cmd, "cmd");

    CreateAccountParams params  = new CreateAccountParams(cmd.getEmail(), cmd.getName(), cmd.getPassword());
    Account             account = this.accountService.create(params);

    if (log.isTraceEnabled()) {
      log.trace(format("create result : account=%s", account));
    }
    return () -> this.accountConverter.convert(account, AccountDto.class);
  }

  @Override
  public Lazy<AccountDto> read(long id) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("read args : id=%d", id));
    }

    Account account = this.accountService.read(id);

    if (log.isTraceEnabled()) {
      log.trace(String.format("read result : account=%s", account));
    }
    return () -> this.accountConverter.convert(account, AccountDto.class);
  }

  @Override
  public Lazy<AccountDto> activate(String code) throws IllegalAccountActivateCodeException {
    if (log.isTraceEnabled()) {
      log.trace(format("activate args : code='%s'", code));
    }

    Account account = this.accountService.activate(code);

    if (log.isTraceEnabled()) {
      log.trace(String.format("activate result : account=%s", account));
    }
    return () -> this.accountConverter.convert(account, AccountDto.class);
  }
}
