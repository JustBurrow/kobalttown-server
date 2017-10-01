package kr.lul.kobalttown.jpa.account.entity;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountCodeReset;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Instant;

/**
 * @author justburrow
 * @since 2017. 10. 1.
 */
@Entity(name = "AccountCodeReset")
@DiscriminatorValue("1")
public class AccountCodeResetEntity extends AbstractAccountCode implements AccountCodeReset {
  private AccountCodeResetEntity() {
  }

  public AccountCodeResetEntity(Account account, String code, Instant expire) {
    super(account, code, expire);
  }
}
