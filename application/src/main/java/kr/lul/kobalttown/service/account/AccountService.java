package kr.lul.kobalttown.service.account;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.service.account.params.CreateAccountParams;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Transactional
public interface AccountService {
  Account create(CreateAccountParams params);
}
