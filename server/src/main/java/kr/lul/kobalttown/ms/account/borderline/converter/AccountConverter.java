package kr.lul.kobalttown.ms.account.borderline.converter;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.support.borderline.DtoConverter;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author justburrow
 * @since 2017. 9. 23.
 */
@Transactional
public interface AccountConverter extends DtoConverter<Account> {
  @Override
  <D> D convert(Account account, Class<D> type);
}
