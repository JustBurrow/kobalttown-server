package kr.lul.kobalttown.dao.account;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountActivateCode;
import kr.lul.kobalttown.jpa.entity.AccountActivateCodeEntity;
import kr.lul.kobalttown.jpa.repository.AccountActivateCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static kr.lul.kobalttown.util.Asserts.notNull;
import static kr.lul.kobalttown.util.Asserts.positive;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Service class AccountActivateCodeDaoImpl implements AccountActivateCodeDao {
  private static final Logger log = LoggerFactory.getLogger(AccountActivateCodeDao.class);

  @Autowired
  private AccountActivateCodeRepository accountActivateCodeRepository;

  @Override
  public AccountActivateCode insert(AccountActivateCode aac) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("insert args : aac=%s", aac));
    }

    if (0L < this.accountActivateCodeRepository.countByCode(aac.getCode())) {
      throw new IllegalArgumentException(String.format("alread exist code : %s", aac.getCode()));
    }

    aac = this.accountActivateCodeRepository.save((AccountActivateCodeEntity) aac);

    if (log.isTraceEnabled()) {
      log.trace(String.format("insert return : %s", aac));
    }
    return aac;
  }

  @Override
  public long count(String code) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("count args : code=%s", code));
    }

    long count = this.accountActivateCodeRepository.countByCode(code);

    if (log.isTraceEnabled()) {
      log.trace(String.format("count return : %s", code));
    }
    return count;
  }

  @Override
  public boolean isExist(Account account) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("isExist args : account=%s", account));
    }
    notNull(account, "account");
    positive(account.getId(), "account.id");

    boolean exists = this.accountActivateCodeRepository.existsByAccount(account);

    if (log.isTraceEnabled()) {
      log.trace(String.format("isExist return : %b", exists));
    }
    return exists;
  }

  @Override
  public void delete(Account account) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("delete args : account=%s", account));
    }
    notNull(account, "account");
    positive(account.getId(), "account.id");

    this.accountActivateCodeRepository.deleteByAccount(account);
  }
}
