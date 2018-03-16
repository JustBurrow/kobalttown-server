package kr.lul.kobalttown.account.dao;

import kr.lul.kobalttown.account.domain.Account;
import kr.lul.kobalttown.account.domain.AccountCode;
import kr.lul.kobalttown.account.domain.AccountCodeType;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Transactional
public interface AccountCodeDao {
  AccountCode insert(AccountCode aac);

  AccountCode select(AccountCodeType type, String code);

  long count(AccountCodeType type, String code);

  boolean isExist(AccountCodeType type, Account account);

  void delete(AccountCodeType type, Account account);
}
