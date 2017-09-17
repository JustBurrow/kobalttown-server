package kr.lul.kobalttown.business.account.dao;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipal;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 8.
 */
@Transactional
public interface AccountDao {
  Account insert(Account account);

  AccountPrincipal insert(AccountPrincipal principal);
}
