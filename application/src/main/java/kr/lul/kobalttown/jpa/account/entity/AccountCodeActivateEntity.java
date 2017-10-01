package kr.lul.kobalttown.jpa.account.entity;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountCodeActivate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Instant;

/**
 * @author justburrow
 * @since 2017. 10. 1.
 */
@Entity(name = "AccountCodeActivate")
@DiscriminatorValue("0")
public class AccountCodeActivateEntity extends AbstractAccountCode implements AccountCodeActivate {
  private AccountCodeActivateEntity() {
  }

  public AccountCodeActivateEntity(Account account, String code, Instant expire) {
    super(account, code, expire);
  }
}
