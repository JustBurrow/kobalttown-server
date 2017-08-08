package kr.lul.kobalttown.service.account;

import kr.lul.kobalttown.dao.account.AccountDao;
import kr.lul.kobalttown.domain.Account;
import kr.lul.kobalttown.jpa.entity.AccountEntity;
import kr.lul.kobalttown.service.account.params.CreateAccountParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static kr.lul.kobalttown.util.Asserts.*;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Service class AccountServiceImpl implements AccountService {
  private static final Logger log = LoggerFactory.getLogger(AccountService.class);

  @Autowired
  private AccountDao accountDao;

  @Override
  public Account create(CreateAccountParams params) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : params=%s", params));
    }
    notNull(params, "params");
    hasLength(params.getEmail(), "params.email");
    notNull(params.getPassword(), "params.password");
    matches(params.getPassword(), "\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}", "params.password");

    Account account = new AccountEntity(params.getEmail(), params.getPassword());
    account = this.accountDao.insert(account);

    if (log.isTraceEnabled()) {
      log.trace(format("result : account=%s", account));
    }
    return account;
  }
}
