package kr.lul.kobalttown.dao.account;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountActivateCode;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 9. 6.
 */
@Transactional
public interface AccountActivateCodeDao {
  AccountActivateCode insert(AccountActivateCode aac);

  AccountActivateCode select(String code);

  long count(String code);

  boolean isExist(Account account);

  void delete(Account account);
}
