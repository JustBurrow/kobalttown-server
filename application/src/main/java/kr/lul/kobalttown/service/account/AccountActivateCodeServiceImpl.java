package kr.lul.kobalttown.service.account;

import kr.lul.kobalttown.dao.account.AccountActivateCodeDao;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountActivateCode;
import kr.lul.kobalttown.jpa.entity.AccountActivateCodeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

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

    Instant             expire = Instant.now().plusMillis(AccountActivateCode.TTL);
    AccountActivateCode aac    = new AccountActivateCodeEntity(account, code, expire);
    aac = this.accountActivateCodeDao.insert(aac);

    if (log.isTraceEnabled()) {
      log.trace(String.format("create return : %s", aac));
    }
    return aac;
  }
}
