package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.dao.AccountActivateCodeDao;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountActivateCode;
import kr.lul.kobalttown.jpa.account.entity.AccountActivateCodeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.lang.String.format;
import static kr.lul.kobalttown.domain.account.AccountActivateCode.TTL;
import static kr.lul.kobalttown.util.Asserts.hasLength;
import static kr.lul.kobalttown.util.Asserts.notNull;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Service class AccountActivateCodeServiceImpl implements AccountActivateCodeService {
  private static final Logger log = LoggerFactory.getLogger(AccountActivateCodeService.class);

  @Autowired
  private AccountActivateCodeDao accountActivateCodeDao;

  @Override
  public AccountActivateCode create(Account account) {
    notNull(account, "account");

    if (this.accountActivateCodeDao.isExist(account)) {
      this.accountActivateCodeDao.delete(account);
    }

    String code;
    do {
      code = randomAlphanumeric(AccountActivateCode.CODE_LENGTH);
    } while (0L < this.accountActivateCodeDao.count(code));

    Instant             expire = Instant.now().plusMillis(TTL);
    AccountActivateCode aac    = new AccountActivateCodeEntity(account, code, expire);
    aac = this.accountActivateCodeDao.insert(aac);

    if (log.isTraceEnabled()) {
      log.trace(format("create return : %s", aac));
    }
    return aac;
  }

  @Override
  public AccountActivateCode read(String code) {
    if (log.isTraceEnabled()) {
      log.trace(format("read args : code='%s'", code));
    }
    hasLength(code, "code");

    AccountActivateCode activateCode = this.accountActivateCodeDao.select(code);

    if (log.isTraceEnabled()) {
      log.trace(String.format("read return : %s", activateCode));
    }
    return activateCode;
  }
}
