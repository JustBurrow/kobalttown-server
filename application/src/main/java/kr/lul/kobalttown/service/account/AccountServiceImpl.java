package kr.lul.kobalttown.service.account;

import kr.lul.kobalttown.domain.Account;
import kr.lul.kobalttown.jpa.entity.AccountEntity;
import kr.lul.kobalttown.jpa.repository.AccountRepository;
import kr.lul.kobalttown.service.account.params.CreateAccountParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Service class AccountServiceImpl implements AccountService {
  private static final Logger log = LoggerFactory.getLogger(AccountService.class);

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public Account create(CreateAccountParams params) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : params=%s", params));
    }

    Account account = new AccountEntity(params.getEmail(), params.getPassword());
    account = this.accountRepository.save((AccountEntity) account);

    if (log.isTraceEnabled()) {
      log.trace(format("result : account=%s", account));
    }
    return account;
  }
}
