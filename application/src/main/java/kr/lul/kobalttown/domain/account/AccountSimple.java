package kr.lul.kobalttown.domain.account;

import kr.lul.kobalttown.util.SimpleString;

import static java.lang.String.format;
import static kr.lul.kobalttown.util.Asserts.notNull;

/**
 * @author justburrow
 * @since 2017. 9. 20.
 */
public class AccountSimple implements SimpleString {
  private long   id;
  private String email;
  private String name;

  public AccountSimple(Account account) {
    notNull(account, "account");
    this.id = account.getId();
    this.email = account.getEmail();
    this.name = account.getName();
  }

  public long getId() {
    return this.id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String toSimpleString() {
    return format("%d:%s<%s>", this.id, this.name, this.email);
  }

  @Override
  public String toString() {
    return new StringBuffer(AccountSimple.class.getSimpleName())
        .append("{id=").append(this.id)
        .append(", email='").append(this.email).append('\'')
        .append(", name='").append(this.name).append('\'')
        .append('}').toString();
  }
}
