package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.service.params.CreateAccountParams;
import kr.lul.kobalttown.business.account.service.params.UpdatePrincipalParams;
import kr.lul.kobalttown.domain.account.Account;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Transactional
public interface AccountService {
  Account create(CreateAccountParams params);

  Account activate(String code) throws IllegalAccountActivateCodeException;

  Account update(UpdatePrincipalParams params);
}
