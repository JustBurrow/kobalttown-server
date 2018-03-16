package kr.lul.kobalttown.account.borderline.converter;

import kr.lul.kobalttown.account.borderline.dto.AccountDto;
import kr.lul.kobalttown.account.domain.Account;
import kr.lul.kobalttown.account.domain.AccountSimple;
import kr.lul.kobalttown.support.borderline.NotSupportedTypeException;
import kr.lul.kobalttown.util.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.lang.String.format;
import static java.util.Collections.unmodifiableSet;
import static kr.lul.kobalttown.util.Asserts.notNull;
import static kr.lul.kobalttown.util.SetUtil.hashSet;

/**
 * @author justburrow
 * @since 2017. 9. 23.
 */
@SuppressWarnings("SpringAutowiredFieldsWarningInspection") @Service
class AccountConverterImpl implements AccountConverter {
  private static final Logger log = LoggerFactory.getLogger(AccountConverter.class);

  private static final Set<Class<?>> SUPPORT_TYPES = unmodifiableSet(
      (Set<Class<?>>) hashSet(AccountDto.class, AccountSimple.class).build());

  @Autowired
  private TimeProvider timeProvider;

  @Override
  public Set<Class<?>> supportTypes() {
    return SUPPORT_TYPES;
  }

  @Override
  public <D> D convert(Account account, Class<D> type) {
    if (log.isTraceEnabled()) {
      log.trace(format("convert args : account=%s, type=%s", account, type));
    }
    notNull(account, "account");
    notNull(type, "type");

    final D dto;
    if (AccountDto.class == type) {
      dto = (D) accountDto(account);
    } else if (AccountSimple.class == type) {
      dto = (D) new AccountSimple(account);
    } else {
      throw new NotSupportedTypeException(format("type : %s", type));
    }

    if (log.isTraceEnabled()) {
      log.trace(format("convert return : %s", dto));
    }
    return dto;
  }

  private AccountDto accountDto(Account account) {
    AccountDto dto = new AccountDto();

    dto.setId(account.getId());
    dto.setName(account.getName());
    dto.setEmail(account.getEmail());
    dto.setEnabled(account.isEnable());
    dto.setCreate(this.timeProvider.zonedDateTime(account.getCreate()));
    dto.setUpdate(this.timeProvider.zonedDateTime(account.getUpdate()));

    return dto;
  }
}
