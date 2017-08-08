package kr.lul.kobalttown.borderline.account.dto;

import kr.lul.kobalttown.domain.Account;
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
  private boolean enabled;

  public AccountDto() {
  }

  public AccountDto(long id, String email, boolean enabled) {
    this.id = id;
    this.email = email;
    this.enabled = enabled;
  }

  public AccountDto(long id, String email, boolean enabled, Instant create, Instant update) {
    super(create, update);
    this.id = id;
    this.email = email;
    this.enabled = enabled;
  }

  public AccountDto(Account account) {
    super(account.getCreate(), account.getUpdate());

    this.id = account.getId();
    this.email = account.getEmail();
    this.enabled = account.isEnable();
  }
}
