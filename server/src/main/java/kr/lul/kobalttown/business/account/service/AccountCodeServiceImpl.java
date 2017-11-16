package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.dao.AccountCodeDao;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountCode;
import kr.lul.kobalttown.domain.account.AccountCodeReset;
import kr.lul.kobalttown.domain.account.AccountCodeType;
import kr.lul.kobalttown.jpa.account.entity.AccountCodeActivateEntity;
import kr.lul.kobalttown.jpa.account.entity.AccountCodeResetEntity;
import kr.lul.kobalttown.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.lang.String.format;
import static kr.lul.kobalttown.domain.account.AccountCode.TTL;
import static kr.lul.kobalttown.util.Asserts.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection") @Service
class AccountCodeServiceImpl implements AccountCodeService {
  private static final Logger log = LoggerFactory.getLogger(AccountCodeService.class);

  @Autowired
  private AccountCodeDao accountCodeDao;
  @Autowired
  private TimeProvider   timeProvider;

  @Override
  public AccountCode createAcitivateCode(Account account) {
    if (log.isTraceEnabled()) {
      log.trace(format("createAcitivateCode args : account=%s", account));
    }
    notNull(account, "account");

    if (this.accountCodeDao.isExist(AccountCodeType.ACTIVATE, account)) {
      this.accountCodeDao.delete(AccountCodeType.ACTIVATE, account);
    }

    String code;
    do {
      code = randomAlphanumeric(AccountCode.CODE_LENGTH);
    } while (0L < this.accountCodeDao.count(AccountCodeType.ACTIVATE, code));

    Instant     expire = this.timeProvider.now().plusMillis(TTL);
    AccountCode aac    = new AccountCodeActivateEntity(account, code, expire);
    aac = this.accountCodeDao.insert(aac);

    if (log.isTraceEnabled()) {
      log.trace(format("createAcitivateCode return : %s", aac));
    }
    return aac;
  }

  @Override
  public AccountCode readActivateCode(String code) {
    if (log.isTraceEnabled()) {
      log.trace(format("readActivateCode args : code='%s'", code));
    }
    hasLength(code, "code");

    AccountCode activateCode = this.accountCodeDao.select(AccountCodeType.ACTIVATE, code);

    if (log.isTraceEnabled()) {
      log.trace(format("readActivateCode return : %s", activateCode));
    }
    return activateCode;
  }

  @Override
  public AccountCodeReset createReset(Account account) {
    if (log.isTraceEnabled()) {
      log.trace(format("createReset args : account=%s", account));
    }
    notNull(account, "account");

    // 기존 코드 확인.
    if (this.accountCodeDao.isExist(AccountCodeType.RESET, account)) {
      this.accountCodeDao.delete(AccountCodeType.RESET, account);
    }

    // 신규 코드의 중복 확인.
    String code;
    do {
      code = randomAlphanumeric(AccountCode.CODE_LENGTH);
    } while (0L < this.accountCodeDao.count(AccountCodeType.RESET, code));

    AccountCodeResetEntity acr = new AccountCodeResetEntity(account, code, this.timeProvider.now().plusMillis(TTL));
    acr = (AccountCodeResetEntity) this.accountCodeDao.insert(acr);

    if (log.isTraceEnabled()) {
      log.trace(format("createReset return : %s", acr));
    }
    return acr;
  }

  @Override
  public AccountCode readResetCode(String code) {
    if (log.isTraceEnabled()) {
      log.trace(format("readResetCode args : code='%s'", code));
    }

    matches(code, AccountCode.CODE_PATTERN, "code");

    AccountCode accountCode = this.accountCodeDao.select(AccountCodeType.RESET, code);

    if (log.isTraceEnabled()) {
      log.trace(format("readResetCode return : %s", accountCode));
    }
    return accountCode;
  }
}
