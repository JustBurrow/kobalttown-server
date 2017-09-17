package kr.lul.kobalttown.jpa.account.entity;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipalEmail;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
@Entity(name = "AccountPrincipalEmail")
@DiscriminatorValue("0")
public class AccountPrincipalEmailEntity extends AbstractAccountPrincipalEntity implements AccountPrincipalEmail {
  private AccountPrincipalEmailEntity() {
  }

  public AccountPrincipalEmailEntity(Account account, String publicKey, String privateKey) {
    super(account, publicKey, privateKey);
  }
}
