package kr.lul.kobalttown.business.account.dao;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipal;
import kr.lul.kobalttown.domain.account.AccountPrincipalType;
import kr.lul.kobalttown.jpa.account.entity.AccountEntity;
import kr.lul.kobalttown.jpa.account.entity.AccountPrincipalEntity;
import kr.lul.kobalttown.jpa.account.repository.AccountPrincipalRepository;
import kr.lul.kobalttown.jpa.account.repository.AccountRepository;
import kr.lul.kobalttown.util.IllegalConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static kr.lul.kobalttown.util.Asserts.*;

/**
 * @author justburrow
 * @since 2017. 8. 8.
 */
@Service class AccountDaoImpl implements AccountDao {
  private static final Logger log = LoggerFactory.getLogger(AccountDao.class);

  @Autowired
  private AccountRepository accountRepository;

  private Map<AccountPrincipalType, AccountPrincipalRepository> accountPrincipalRepositoryMap;

  @Autowired
  private void setAccountPrincipalRepositories(List<AccountPrincipalRepository> repositories) {
    if (log.isTraceEnabled()) {
      log.trace(format("repositories=%s", repositories));
    }
    if (null == repositories || repositories.isEmpty()) {
      IllegalConfigurationException e = new IllegalConfigurationException(
          "account principal repositories is " + (null == repositories ? "null." : "empty."));
      log.error("bean autowiring fail.", e);
      throw e;
    }

    this.accountPrincipalRepositoryMap = repositories
        .stream()
        .collect(Collectors.toMap(AccountPrincipalRepository::supportType, repository -> repository));
  }

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

  @Override
  public Account select(long id) {
    if (log.isTraceEnabled()) {
      log.trace(format("select args : id=%d", id));
    }

    Account account = this.accountRepository.findOne(id);

    if (log.isTraceEnabled()) {
      log.trace(format("select return : %s", account));
    }
    return account;
  }

  @Override
  public Account select(String name) {
    if (log.isTraceEnabled()) {
      log.trace(format("select args : name='%s'", name));
    }

    AccountEntity account = this.accountRepository.findOneByName(name);

    if (log.isTraceEnabled()) {
      log.trace(format("select return : %s", account));
    }
    return account;
  }

  /**
   * @@since 2017. 9. 28.
   */
  @Override
  public Account selectEmail(String email) {
    if (log.isTraceEnabled()) {
      log.trace(String.format("selectEmail args : email='%s'", email));
    }
    hasLength(email, "email");

    Account account = this.accountRepository.findOneByEmail(email);

    if (log.isTraceEnabled()) {
      log.trace(String.format("selectEmail return : %s", account));
    }
    return account;
  }

  @Override
  public AccountPrincipal insert(AccountPrincipal principal) {
    if (log.isTraceEnabled()) {
      log.trace(format("args : principal=%s", principal));
    }
    notNull(principal, "principal");
    positive(principal.getAccount().getId(), "principal.account.id");

    principal = (AccountPrincipal) this.accountPrincipalRepositoryMap
        .get(principal.getType())
        .save((AccountPrincipalEntity) principal);

    if (log.isTraceEnabled()) {
      log.trace(format("return=%s", principal));
    }
    return principal;
  }

  @Override
  public AccountPrincipal selectPrincipal(AccountPrincipalType type, Account account) {
    if (log.isTraceEnabled()) {
      log.trace(format("selectPrincipal args : account=%s", account));
    }
    notNull(type, "type");
    notNull(account, "account");
    assignable(account, AccountEntity.class, "account");

    AccountPrincipalEntity principal = this.accountPrincipalRepositoryMap
        .get(type)
        .findOneByAccount((AccountEntity) account);

    if (log.isTraceEnabled()) {
      log.trace(format("selectPrincipal return : %s", principal));
    }
    return principal;
  }

  @Override
  public void delete(AccountPrincipal principal) {
    if (log.isTraceEnabled()) {
      log.trace(format("delete args : principal=%s", principal));
    }

    AccountPrincipalRepository repository = this.accountPrincipalRepositoryMap.get(principal.getType());
    repository.delete(principal);
    repository.flush();
  }
}

