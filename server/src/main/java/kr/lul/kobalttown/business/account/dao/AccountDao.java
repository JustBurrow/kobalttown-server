package kr.lul.kobalttown.business.account.dao;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipal;
import kr.lul.kobalttown.domain.account.AccountPrincipalType;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 8.
 */
@Transactional
public interface AccountDao {
  Account insert(Account account);

  Account select(long id);

  Account select(String name);

  /**
   * @param email
   * @return
   * @@since 2017. 9. 28.
   */
  Account selectEmail(String email);

  AccountPrincipal insert(AccountPrincipal principal);

  AccountPrincipal selectPrincipal(AccountPrincipalType type, Account account);

  void delete(AccountPrincipal principal);
}
