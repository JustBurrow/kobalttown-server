package kr.lul.kobalttown.account.dao;

import kr.lul.kobalttown.account.domain.Account;
import kr.lul.kobalttown.account.domain.AccountCode;
import kr.lul.kobalttown.account.domain.AccountCodeType;
import kr.lul.kobalttown.account.jpa.entity.AbstractAccountCode;
import kr.lul.kobalttown.account.jpa.repository.AccountCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static kr.lul.kobalttown.account.domain.AccountCode.CODE_PATTERN;
import static kr.lul.kobalttown.util.Asserts.*;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection") @Service
class AccountCodeDaoImpl implements AccountCodeDao {
  private static final Logger log = LoggerFactory.getLogger(AccountCodeDao.class);

  @Autowired
  private AccountCodeRepository accountCodeRepository;

  @Override
  public AccountCode insert(AccountCode ac) {
    if (log.isTraceEnabled()) {
      log.trace(format("insert args : ac=%s", ac));
    }
    notNull(ac, "ac");

    if (0L < this.accountCodeRepository.countByTypeAndCode(ac.getType(), ac.getCode())) {
      throw new IllegalArgumentException(format("alread exist code : %s", ac.getCode()));
    }

    ac = this.accountCodeRepository.save((AbstractAccountCode) ac);

    if (log.isTraceEnabled()) {
      log.trace(format("insert return : %s", ac));
    }
    return ac;
  }

  @Override
  public AccountCode select(AccountCodeType type, String code) {
    if (log.isTraceEnabled()) {
      log.trace(format("select args : code='%s'", code));
    }
    notNull(type, "type");
    matches(code, CODE_PATTERN, "code");

    AbstractAccountCode activateCode = this.accountCodeRepository.findOneByTypeAndCode(type, code);

    if (log.isTraceEnabled()) {
      log.trace(format("select return : %s", activateCode));
    }
    return activateCode;
  }

  @Override
  public long count(AccountCodeType type, String code) {
    if (log.isTraceEnabled()) {
      log.trace(format("count args : code=%s", code));
    }
    notNull(type, "type");
    matches(code, CODE_PATTERN, "code");

    long count = this.accountCodeRepository.countByTypeAndCode(type, code);

    if (log.isTraceEnabled()) {
      log.trace(format("count return : %s", code));
    }
    return count;
  }

  @Override
  public boolean isExist(AccountCodeType type, Account account) {
    if (log.isTraceEnabled()) {
      log.trace(format("isExist args : account=%s", account));
    }
    notNull(type, "type");
    notNull(account, "account");
    positive(account.getId(), "account.id");

    boolean exists = this.accountCodeRepository.existsByTypeAndAccount(type, account);

    if (log.isTraceEnabled()) {
      log.trace(format("isExist return : %b", exists));
    }
    return exists;
  }

  @Override
  public void delete(AccountCodeType type, Account account) {
    if (log.isTraceEnabled()) {
      log.trace(format("delete args : account=%s", account));
    }
    notNull(type, "type");
    notNull(account, "account");
    positive(account.getId(), "account.id");

    this.accountCodeRepository.deleteByTypeAndAccount(type, account);
  }
}
