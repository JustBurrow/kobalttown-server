package kr.lul.kobalttown.dao.account;

import kr.lul.kobalttown.domain.account.Account;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 8.
 */
@Transactional
public interface AccountDao {
  Account insert(Account account);
}
