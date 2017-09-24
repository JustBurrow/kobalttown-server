package kr.lul.kobalttown.business.account.service;

import kr.lul.kobalttown.business.account.exception.IllegalAccountActivateCodeException;
import kr.lul.kobalttown.business.account.service.params.CreateAccountParams;
import kr.lul.kobalttown.business.account.service.params.UpdateAccountParams;
import kr.lul.kobalttown.business.account.service.params.UpdatePrincipalParams;
import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.util.PropertyException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 8. 6.
 */
@Transactional
public interface AccountService {
  Account create(CreateAccountParams params);

  Account read(long id);

  Account activate(String code) throws IllegalAccountActivateCodeException;

  Account update(UpdateAccountParams params) throws PropertyException;

  Account update(UpdatePrincipalParams params);
}
