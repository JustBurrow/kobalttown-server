package kr.lul.kobalttown.jpa.account.entity;

import kr.lul.kobalttown.domain.account.Account;
import kr.lul.kobalttown.domain.account.AccountPrincipalEmail;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static kr.lul.kobalttown.util.Asserts.bcrypt;
import static kr.lul.kobalttown.util.Asserts.hasLength;

/**
 * @author justburrow
 * @since 2017. 8. 18.
 */
@Entity(name = "AccountPrincipalEmail")
@DiscriminatorValue("0")
public class AccountPrincipalEmailEntity extends AccountPrincipalEntity implements AccountPrincipalEmail {
  private AccountPrincipalEmailEntity() {
  }

  public AccountPrincipalEmailEntity(Account account, String publicKey, String privateKey) {
    super(account, publicKey, privateKey);
  }

  @Override
  protected void setPublicKey(String email) {
    hasLength(email, "email");
    this.publicKey = email;
  }

  @Override
  protected void setPrivateKey(String password) {
    bcrypt(password, "password");
    this.privateKey = password;
  }
}
