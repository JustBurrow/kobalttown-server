package kr.lul.kobalttown.ms.account.borderline.dto;

import kr.lul.kobalttown.domain.account.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;

/**
 * @author justburrow
 * @since 2017. 8. 7.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountDto extends UpdatableDto {
  private long    id;
  private String  email;
  private String  name;
  private boolean enabled;

  public AccountDto() {
  }

  public AccountDto(long id, String email, String name, boolean enabled) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.enabled = enabled;
  }

  public AccountDto(long id, String email, String name, boolean enabled, Instant create, Instant update) {
    super(create, update);
    this.id = id;
    this.email = email;
    this.name = name;
    this.enabled = enabled;
  }

  public AccountDto(Account account) {
    super(account.getCreate(), account.getUpdate());

    this.id = account.getId();
    this.email = account.getEmail();
    this.name = account.getName();
    this.enabled = account.isEnable();
  }
}
