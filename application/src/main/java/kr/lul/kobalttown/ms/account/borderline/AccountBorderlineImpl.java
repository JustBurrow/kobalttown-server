package kr.lul.kobalttown.ms.account.borderline;

import kr.lul.kobalttown.business.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.business.account.service.AccountService;
import kr.lul.kobalttown.business.account.service.params.CreateAccountParams;
import kr.lul.kobalttown.business.account.service.params.UpdateAccountParams;
import kr.lul.kobalttown.business.account.service.params.UpdatePrincipalParams;
import kr.lul.kobalttown.business.exception.DataNotExistException;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.ms.account.borderline.cmd.CreateAccountCmd;
import kr.lul.kobalttown.ms.account.borderline.cmd.UpdateAccountBasicCmd;
import kr.lul.kobalttown.ms.account.borderline.cmd.UpdatePasswordCmd;
import kr.lul.kobalttown.ms.account.borderline.converter.AccountConverter;
import kr.lul.kobalttown.ms.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.util.Lazy;
import kr.lul.kobalttown.util.PropertyException;
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
  public Lazy<AccountDto> update(UpdateAccountBasicCmd cmd) throws PropertyException {
    if (log.isTraceEnabled()) {
      log.trace(String.format("update args : cmd=%s", cmd));
    }

    Account operator = this.accountService.read(cmd.getOperator().getId());
    if (null == operator) {
      throw new DataNotExistException(format("account does not exist : account=%s", cmd.getOperator()));
    }

    UpdateAccountParams params = new UpdateAccountParams(operator);
    params.setName(cmd.getName());
    final Account account = this.accountService.update(params);

    if (log.isTraceEnabled()) {
      log.trace(String.format("update result : account=%s", operator));
    }
    return () -> this.accountConverter.convert(account, AccountDto.class);
  }

  @Override
  public Lazy<AccountDto> update(UpdatePasswordCmd cmd) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("update args : cmd=%s", cmd));
    }

    Account operator = this.accountService.read(cmd.getOperator().getId());
    if (null == operator) {
      throw new DataNotExistException(format("illegal operator : cmd.operator=%s", cmd.getOperator()));
    }

    UpdatePrincipalParams params = new UpdatePrincipalParams(operator);
    params.setType(cmd.getType());
    params.setPublicKey(cmd.getPublickKey());
    params.setOldPrivateKey(cmd.getCurrent());
    params.setNewPrivateKey(cmd.getPassword());
    Account account = this.accountService.update(params);

    if (log.isTraceEnabled()) {
      log.trace(String.format("update result : account=%s", account));
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
