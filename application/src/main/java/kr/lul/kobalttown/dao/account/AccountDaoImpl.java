package kr.lul.kobalttown.dao.account;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.jpa.entity.AccountEntity;
import kr.lul.kobalttown.jpa.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static kr.lul.kobalttown.util.Asserts.isNull;
import static kr.lul.kobalttown.util.Asserts.zero;

/**
 * @author justburrow
 * @since 2017. 8. 8.
 */
@Service class AccountDaoImpl implements AccountDao {
  private static final Logger log = LoggerFactory.getLogger(AccountDao.class);

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public Account insert(Account account) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : account=%s", account));
    }
    zero(account.getId(), "account.id");
    isNull(account.getCreate(), "account.create");
    isNull(account.getUpdate(), "account.update");

    account = this.accountRepository.save((AccountEntity) account);

    if (log.isTraceEnabled()) {
      log.trace(format("return : account=%s", account));
    }
    return account;
  }
}
